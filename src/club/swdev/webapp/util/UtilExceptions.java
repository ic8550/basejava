package club.swdev.webapp.util;

import club.swdev.webapp.exception.ItemAlreadyPresentInStorageException;
import club.swdev.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class UtilExceptions {
    private UtilExceptions() {
    }

    public static StorageException getStorageException(SQLException e) {
        if (e instanceof PSQLException) {
            /*
             * https://www.postgresql.org/docs/current/errcodes-appendix.html
             * Class 23 — Integrity Constraint Violation
             * 23505 unique_violation
             */
            if (e.getSQLState().equals("23505")) {
                return new ItemAlreadyPresentInStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
