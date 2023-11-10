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

public class EmpleadoDAO {
    Connection conn = null;

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



    }







