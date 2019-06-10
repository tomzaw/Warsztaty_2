package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Not used addition.
public class DbService {

    private static DbService DBSERVICE;
    static String db;
    static String user;
    static String password;

    private DbService() {
    }

    public static DbService getInstance() {

        if (DBSERVICE == null) {
            DBSERVICE = new DbService();
        }
        return DBSERVICE;
    }

    //Set db before using other methods.
    public void setDb(String db, String user, String password) {

        DbService.db = db;
        DbService.user = user;
        DbService.password = password;
    }

    private Connection createCon() throws SQLException {

        String conUrl = "jdbc:mysql://localhost:3306/" + db + "?useSSL=false&characterEncoding=utf8&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        return DriverManager.getConnection(conUrl, user, password);
    }

    public Integer insertIntoDatabase(String query, List<String> vargs) throws SQLException {

        try (Connection con = createCon()) {

            String[] generatedColumns = {"id"};
            PreparedStatement stmt = con.prepareStatement(query, generatedColumns);

            if (vargs != null) {
                int i = 1;
                for (String p : vargs) {

                    stmt.setString(i++, p);
                }
            }

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return null;
            }

        } catch (SQLException e) {

            throw e;
        }
    }

    public Result execute(String query, String... vargs) throws SQLException {

        Result result = new Result();

        if (query.trim().toUpperCase().startsWith("Select")) {

            result.rows = getData(query, vargs);

        } else if (query.trim().toUpperCase().startsWith("Update") || query.trim().toUpperCase().startsWith("Delete")
                || query.trim().toUpperCase().startsWith("Drop") || query.trim().toUpperCase().startsWith("Insert")
                || query.trim().toUpperCase().startsWith("Alter")) {

            result.affectedRowsCount = executeUpdate(query, vargs);

        } else {

            throw new RuntimeException("Wrong statement");
        }

        return result;
    }

    private List<String[]> getData(String query, String... vargs) throws SQLException {

        try (Connection con = createCon()) {

            PreparedStatement stmt = getPreparedStatement(query, con, vargs);

            ResultSet rs = stmt.executeQuery();

            int cols = rs.getMetaData().getColumnCount();
            List<String[]> rows = new ArrayList();

            while (rs.next()) {

                String[] row = new String[cols];
                for (int i = 0; i < cols; i++) {

                    row[i] = rs.getString(i + 1);
                }
                rows.add(row);
            }

            return rows;

        } catch (SQLException e) {

            throw e;
        }
    }

    private PreparedStatement getPreparedStatement(String query, Connection con, String... params) throws SQLException {

        PreparedStatement stmt = con.prepareStatement(query);
        if (params != null) {
            int i = 1;
            for (String p : params) {

                stmt.setString(i++, p);
            }
        }
        return stmt;
    }

    private int executeUpdate(String query, String... params) throws SQLException {

        try (Connection con = createCon()) {

            PreparedStatement stmt = getPreparedStatement(query, con, params);
            return stmt.executeUpdate();

        } catch (SQLException e) {

            throw e;
        }
    }

    public class Result {

        private Integer affectedRowsCount;
        private List<String[]> rows;

        public Integer getAffectedRowsCount() {
            return affectedRowsCount;
        }

        public List<String[]> getRows() {
            return rows;
        }
    }
}
