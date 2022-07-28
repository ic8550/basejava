package club.swdev.webapp.storage;

import club.swdev.webapp.exception.ItemNotPresentInStorageException;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.sql.ConnectionFactory;
import club.swdev.webapp.sql.SqlExecutor;
import club.swdev.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        this.sqlHelper = new SqlHelper(new ConnectionFactory() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        });
    }

    @Override
    public int size() {
        return sqlHelper.executeSqlStatement("SELECT count(*) FROM resume", new SqlExecutor<Integer>() {
            @Override
            public Integer executeSql(PreparedStatement sqlStatement) throws SQLException {
                ResultSet queryResult = sqlStatement.executeQuery();
                return queryResult.next() ? queryResult.getInt(1) : 0;
            }
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.executeSqlStatement("SELECT * FROM resume r WHERE r.uuid =?", new SqlExecutor<Resume>() {
            @Override
            public Resume executeSql(PreparedStatement sqlStatement) throws SQLException {
                sqlStatement.setString(1, uuid);
                ResultSet queryResult = sqlStatement.executeQuery();
                if (!queryResult.next()) {
                    throw new ItemNotPresentInStorageException(uuid);
                }
                return new Resume(uuid, queryResult.getString("full_name"));
            }
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.executeSqlStatement("SELECT * FROM resume r ORDER BY full_name,uuid", new SqlExecutor<List<Resume>>() {
            @Override
            public List<Resume> executeSql(PreparedStatement sqlStatement) throws SQLException {
                ResultSet queryResult = sqlStatement.executeQuery();
                List<Resume> resumes = new ArrayList<>();
                while (queryResult.next()) {
                    resumes.add(new Resume(queryResult.getString("uuid"), queryResult.getString("full_name")));
                }
                return resumes;
            }
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.executeSqlStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)", new SqlExecutor<Object>() {
            @Override
            public Object executeSql(PreparedStatement sqlStatement) throws SQLException {
                sqlStatement.setString(1, resume.getUuid());
                sqlStatement.setString(2, resume.getFullName());
                sqlStatement.execute();
                return null;
            }
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.executeSqlStatement("UPDATE resume SET full_name = ? WHERE uuid = ?", new SqlExecutor<Object>() {
            @Override
            public Object executeSql(PreparedStatement sqlStatement) throws SQLException {
                sqlStatement.setString(1, resume.getFullName());
                sqlStatement.setString(2, resume.getUuid());
                if (sqlStatement.executeUpdate() == 0) {
                    throw new ItemNotPresentInStorageException(resume.getUuid());
                }
                return null;
            }
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.executeSqlStatement("DELETE FROM resume WHERE uuid=?", new SqlExecutor<Object>() {
            @Override
            public Object executeSql(PreparedStatement sqlStatement) throws SQLException {
                sqlStatement.setString(1, uuid);
                if (sqlStatement.executeUpdate() == 0) {
                    throw new ItemNotPresentInStorageException(uuid);
                }
                return null;
            }
        });
    }

    @Override
    public void clear() {
        sqlHelper.executeSqlStatement("DELETE FROM resume", new SqlExecutor<Object>() {
            @Override
            public Object executeSql(PreparedStatement sqlStatement) throws SQLException {
                sqlStatement.execute();
                return null;
            }
        });
    }
}
