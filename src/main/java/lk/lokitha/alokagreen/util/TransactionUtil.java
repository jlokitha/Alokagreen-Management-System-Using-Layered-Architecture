package lk.lokitha.alokagreen.util;

import lk.lokitha.alokagreen.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtil {
    static Connection connection;

    static {
        try {
            connection = DbConnection.getInstance( ).getConnection( );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TransactionUtil() {
    }

    public static void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public static void endTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public static void rollBack() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }
}
