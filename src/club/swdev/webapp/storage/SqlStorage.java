package club.swdev.webapp.storage;

import club.swdev.webapp.exception.ItemNotPresentInStorageException;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        this.sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public int size() {
        return sqlHelper.<Integer>executeSqlStatement("SELECT count(*) FROM resume", (preparedSqlStatement) -> {
            ResultSet queryResult = preparedSqlStatement.executeQuery();
            return queryResult.next() ? queryResult.getInt(1) : 0;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.<Resume>executeSqlStatement("SELECT * FROM resume r WHERE r.uuid =?", (preparedSqlStatement) -> {
            preparedSqlStatement.setString(1, uuid);
            ResultSet queryResult = preparedSqlStatement.executeQuery();
            if (!queryResult.next()) {
                throw new ItemNotPresentInStorageException(uuid);
            }
            return new Resume(uuid, queryResult.getString("full_name"));
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.<List<Resume>>executeSqlStatement("SELECT * FROM resume r ORDER BY full_name,uuid", (preparedSqlStatement) -> {
            List<Resume> resumes = new ArrayList<>();
            ResultSet queryResult = preparedSqlStatement.executeQuery();
            while (queryResult.next()) {
                resumes.add(new Resume(queryResult.getString("uuid"), queryResult.getString("full_name")));
            }
            return resumes;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.<Void>executeSqlStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)", (preparedSqlStatement) -> {
            preparedSqlStatement.setString(1, resume.getUuid());
            preparedSqlStatement.setString(2, resume.getFullName());
            preparedSqlStatement.execute();
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.<Void>executeSqlStatement("UPDATE resume SET full_name = ? WHERE uuid = ?", (preparedSqlStatement) -> {
            preparedSqlStatement.setString(1, resume.getFullName());
            preparedSqlStatement.setString(2, resume.getUuid());
            if (preparedSqlStatement.executeUpdate() == 0) {
                throw new ItemNotPresentInStorageException(resume.getUuid());
            }
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.<Void>executeSqlStatement("DELETE FROM resume WHERE uuid=?", (preparedSqlStatement) -> {
            preparedSqlStatement.setString(1, uuid);
            if (preparedSqlStatement.executeUpdate() == 0) {
                throw new ItemNotPresentInStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public void clear() {
        sqlHelper.<Void>executeSqlStatement("DELETE FROM resume", (preparedSqlStatement) -> {
            preparedSqlStatement.execute();
            return null;
        });
    }
}
