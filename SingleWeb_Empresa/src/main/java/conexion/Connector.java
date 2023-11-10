package conexion;


import java.sql.*;
public class Connector {
    public static Connection getConnection() throws SQLException {
        final String USER = "root";
        final String PASS = "123456";
        final String DB_NAME = "empresa";
        final String CONN_URL = "jdbc:mariadb://localhost:3306/" + DB_NAME;
        Connection conn = null;

        conn = DriverManager.getConnection(CONN_URL, USER, PASS);
        return conn;
    }

    public static void close(Connection conn) throws SQLException {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}