package dao;

import conexion.Connector;
import model.Sueldo;

import java.sql.*;


public class NominaDAO {
    Connection conn = null;
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


}
