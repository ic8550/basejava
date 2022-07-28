package club.swdev.webapp.sql;

import club.swdev.webapp.util.UtilExceptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T executeSqlStatement(String sqlQuery, SqlExecutor<T> sqlExecutor) {
        try (Connection dbConn = connectionFactory.getConnection();
             PreparedStatement sqlStatement = dbConn.prepareStatement(sqlQuery)) {
            return sqlExecutor.executeSql(sqlStatement);
        } catch (SQLException e) {
            throw UtilExceptions.getStorageException(e);
        }
    }
}
