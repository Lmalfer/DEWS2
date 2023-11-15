package dao;

import conexion.Connector;
import model.Sueldo;

import java.sql.*;

/**
 * Clase que maneja las operaciones de acceso a la base de datos relacionadas con la nómina de los empleados.
 */
public class NominaDAO {
    Connection conn = null;
    /**
     * Encuentra el sueldo de un empleado por su número de identificación (DNI).
     *
     * @param dniEmpleado El DNI del empleado.
     * @return Objeto Sueldo con el sueldo del empleado encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public Sueldo findSueldoByDni(String dniEmpleado) throws SQLException {
        Sueldo sueldoEmpleado = null;

        try {
            conn = Connector.getConnection();
            PreparedStatement pt = null;

            String query = "SELECT  dni, sueldo FROM nominas WHERE dni = ?";
            pt = conn.prepareStatement(query);

            pt.setString(1, dniEmpleado);

            ResultSet rs = pt.executeQuery();

            if (rs.next()) {
                String dni = rs.getString("dni");
                double sueldo = rs.getDouble("sueldo");

                sueldoEmpleado = new Sueldo(dni, sueldo);

            } else {

                System.out.println("No se encontró ningún empleado con el DNI proporcionado");

            }

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return sueldoEmpleado;
    }
    /**
     * Actualiza el sueldo de un empleado en la base de datos.
     *
     * @param dniEmpleado El DNI del empleado cuyo sueldo se va a actualizar.
     * @param sueldo      El nuevo sueldo del empleado.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public void actualizarSueldo(String dniEmpleado, double sueldo) throws SQLException {

        try {
            conn = Connector.getConnection();
            PreparedStatement pt = null;

            String query = "UPDATE nominas SET sueldo = ? WHERE dni = ?";
            pt = conn.prepareStatement(query);
            pt.setDouble(1, sueldo);
            pt.setString(2, dniEmpleado);

            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Sueldo actualizado");
            } else {
                System.out.println("No se encontró ningún empleado con el DNI proporcionado");
            }


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
     * Elimina el registro de sueldo de un empleado de la base de datos usando su DNI.
     *
     * @param dni El DNI del empleado cuyo sueldo se eliminará.
     * @throws SQLException Si ocurre un error de SQL.
     */
    public void eliminarSueldo(String dni) throws SQLException {

        try {
            conn = Connector.getConnection();
            PreparedStatement pt = null;

            String query = "DELETE FROM nominas WHERE dni = ?";
            pt = conn.prepareStatement(query);
            pt.setString(1, dni);



            int rowCount = pt.executeUpdate();

            if (rowCount > 0) {
                System.out.println("Sueldo actualizado");
            } else {
                System.out.println("No se encontró ningún empleado con el DNI proporcionado");
            }


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
