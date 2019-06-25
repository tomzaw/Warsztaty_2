package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tomek
 */
//Set connection in this class or use setDb.
public class DbUtil {

    private static String db = "bootcamp";
    private static String user = "root";
    private static String password = "root";

    public static Connection getCon() throws SQLException {

        String conUrl = "jdbc:mysql://localhost:3306/" + db + "?useSSL=false&characterEncoding=utf8&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        return DriverManager.getConnection(conUrl, user, password);
    }

    public void setDb(String db, String user, String password) {

        DbUtil.db = db;
        DbUtil.user = user;
        DbUtil.password = password;
    }
}
