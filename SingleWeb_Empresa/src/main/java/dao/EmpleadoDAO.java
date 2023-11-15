package dao;

import conexion.Connector;
import exception.DatosNoCorrectosException;
import model.Empleado;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para acceder a la base de datos y realizar operaciones relacionadas con los empleados.
 */
public class EmpleadoDAO {
    Connection conn = null;

    /**
     * Obtiene la lista de empleados desde la base de datos.
     *
     * @return Una lista de objetos Empleado.
     * @throws SQLException            Si ocurre un error de SQL.
     * @throws DatosNoCorrectosException Si los datos de los empleados no son correctos.
     */
    public List<Empleado> obtenerEmpleados() throws SQLException, DatosNoCorrectosException {
        List<Empleado> empleados = new ArrayList<>();
        String query = "SELECT * FROM empleados";

        try (Connection conn = Connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                char sexo = rs.getString("sexo").charAt(0);
                int categoria = rs.getInt("categoria");
                double anyos = rs.getDouble("anyos");

                Empleado empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
                empleados.add(empleado);
            }
        }

        return empleados;
    }
    /**
     * Modifica los detalles de un empleado en la base de datos.
     *
     * @param empleado El objeto Empleado con los detalles actualizados.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public void modificarEmpleado(Empleado empleado) throws SQLException {

        String query = "UPDATE empleados SET nombre = ?, sexo = ?, categoria = ?, anyos = ? WHERE dni = ?";
        try (Connection conn = Connector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, String.valueOf(empleado.getSexo()));
            stmt.setInt(3, empleado.getCategoria());
            stmt.setDouble(4, empleado.getAnyos());
            stmt.setString(5, empleado.getDni());

            int rowCount = stmt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Empleado modificado correctamente");
            } else {
                System.out.println("No se encontró ningún empleado con el ID proporcionado");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió algún error al conectar u operar con la BD");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * Elimina un empleado de la base de datos usando el DNI.
     *
     * @param dni El DNI del empleado a eliminar.
     */
    public void eliminarEmpleadoPorDNI(String dni) {
        String query = "DELETE FROM empleados WHERE dni = ?";

        try {
            conn = Connector.getConnection();
            PreparedStatement pt = null;

            pt = conn.prepareStatement(query);

            pt.setString(1, dni);

            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Empleado modificado correctamente");
            } else {
                System.out.println("No se encontró ningún empleado con el DNI proporcionado");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió algún error al conectar u operar con la BD");
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


            }








