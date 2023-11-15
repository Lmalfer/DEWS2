package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión a la base de datos.
 */
public class Connector {

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return La conexión a la base de datos.
     * @throws SQLException Si ocurre un error al intentar establecer la conexión.
     */
    public static Connection getConnection() throws SQLException {
        final String USER = "root";
        final String PASS = "123456";
        final String DB_NAME = "empresa";
        final String CONN_URL = "jdbc:mariadb://localhost:3306/" + DB_NAME;

        Connection conn = DriverManager.getConnection(CONN_URL, USER, PASS);
        return conn;
    }

    /**
     * Cierra la conexión a la base de datos.
     *
     * @param conn La conexión a cerrar.
     * @throws SQLException Si ocurre un error al intentar cerrar la conexión.
     */
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
