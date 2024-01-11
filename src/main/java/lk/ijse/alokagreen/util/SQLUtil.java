package lk.ijse.alokagreen.util;

import lk.ijse.alokagreen.db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T> T execute(String sql, Object... args) throws SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement( sql );

        for (int i = 0; i < args.length; i++) {
            stm.setObject( (i + 1), args[i] );
        }

        if (sql.trim().toUpperCase().startsWith( "SELECT" )) {
            return (T) stm.executeQuery();
        } else {
            return (T) (Boolean) (stm.executeUpdate() > 0);
        }
    }
}
