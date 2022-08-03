package club.swdev.webapp.storage;

import club.swdev.webapp.exception.ItemNotPresentInStorageException;
import club.swdev.webapp.model.ContactType;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public int size() {
        return sqlHelper.executeSql("SELECT count(*) FROM resume", st -> {
            ResultSet queryResult = st.executeQuery();
            return queryResult.next() ? queryResult.getInt(1) : 0;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.executeSql("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid =? ",
                preparedSql -> {
                    preparedSql.setString(1, uuid);
                    ResultSet queryResult = preparedSql.executeQuery();
                    if (!queryResult.next()) {
                        throw new ItemNotPresentInStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, queryResult.getString("full_name"));
                    do {
                        addContact(queryResult, resume);
                    } while (queryResult.next());

                    return resume;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.executeSql("" +
                "   SELECT * FROM resume r\n" +
                "LEFT JOIN contact c ON r.uuid = c.resume_uuid\n" +
                "ORDER BY full_name, uuid", preparedSql -> {
            ResultSet queryResult = preparedSql.executeQuery();
            // Map<String, Resume> resumeMap = new HashMap<>();
            Map<String, Resume> resumeMap = new LinkedHashMap<>();
            while (queryResult.next()) {
                String uuid = queryResult.getString("uuid");
                Resume resume = resumeMap.get(uuid);
                if (resume == null) {
                    resume = new Resume(uuid, queryResult.getString("full_name"));
                    resumeMap.put(uuid, resume);
                }
                addContact(queryResult, resume);
            }
            return  new ArrayList<>(resumeMap.values());
            // List<Resume> resumes = new ArrayList<>(resumeMap.values());
            // resumes.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
            // return resumes;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.executeTransaction(dbConn -> {
            try (PreparedStatement preparedSql = dbConn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                preparedSql.setString(1, resume.getUuid());
                preparedSql.setString(2, resume.getFullName());
                preparedSql.execute();
            }
            insertContact(dbConn, resume);
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.executeTransaction(dbConn -> {
            try (PreparedStatement preparedSql = dbConn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                preparedSql.setString(1, resume.getFullName());
                preparedSql.setString(2, resume.getUuid());
                if (preparedSql.executeUpdate() != 1) {
                    throw new ItemNotPresentInStorageException(resume.getUuid());
                }
            }
            deleteContacts(dbConn, resume);
            insertContact(dbConn, resume);
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.executeSql("DELETE FROM resume WHERE uuid=?", preparedSql -> {
            preparedSql.setString(1, uuid);
            if (preparedSql.executeUpdate() == 0) {
                throw new ItemNotPresentInStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public void clear() {
        sqlHelper.executeSql("DELETE FROM resume");
    }

    private void deleteContacts(Connection conn, Resume resume) {
        sqlHelper.executeSql("DELETE  FROM contact WHERE resume_uuid=?", preparedSql -> {
            preparedSql.setString(1, resume.getUuid());
            preparedSql.execute();
            return null;
        });
    }

    private void insertContact(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement preparedSql = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> contacts : resume.getContacts().entrySet()) {
                preparedSql.setString(1, resume.getUuid());
                preparedSql.setString(2, contacts.getKey().name());
                preparedSql.setString(3, contacts.getValue());
                preparedSql.addBatch();
            }
            preparedSql.executeBatch();
        }
    }

    private void addContact(ResultSet queryResult, Resume resume) throws SQLException {
        String value = queryResult.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(queryResult.getString("type")), value);
        }
    }
}