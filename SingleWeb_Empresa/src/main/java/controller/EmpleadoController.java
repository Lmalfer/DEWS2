package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpleadoDAO;
import dao.NominaDAO;
import exception.DatosNoCorrectosException;
import model.*;

/**
 * Controlador para la gestión de empleados y nóminas en la base de datos.
 */
@WebServlet(description = "administra peticiones para la base de datos de gestión de nóminas", urlPatterns = {"/gestor"})

public class EmpleadoController extends HttpServlet {
    /**
     * Constructor por defecto.
     */
    public EmpleadoController() {
        super();
    }
    /**
     * Inicializa el servlet.
     *
     * @throws ServletException Si hay un error en la inicialización del servlet.
     */
    public void init() throws ServletException {
        try {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } finally {

        }
    }
    /**
     * Maneja las peticiones GET.
     *
     * @param request  La solicitud HTTP recibida.
     * @param response La respuesta HTTP que se enviará.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      Si hay un error de entrada/salida al manejar la solicitud.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        String content = "";


        if ("listar".equals(opcion)) {
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();

            try {
                List<Empleado> lista = empleadoDAO.obtenerEmpleados();
                content = "views/listaempleados.jsp";

                request.setAttribute("lista", lista);
                request.setAttribute("content", content);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (DatosNoCorrectosException e) {
                throw new RuntimeException(e);
            }

        } else if ("consultarSueldo".equals(opcion)) {

            content = "views/formsueldo.jsp";

            request.setAttribute("content", content);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);

        } else if ("sueldoByDni".equals(opcion)) {
            String dni = request.getParameter("dni");

            if (dni != null && !dni.isEmpty()) {
                NominaDAO nominaDAO = new NominaDAO();
                double sueldo = 0;
                try {
                    Sueldo sueldoObj = nominaDAO.findSueldoByDni(dni);
                    if (sueldoObj != null) {
                        sueldo = sueldoObj.getSueldo();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                // Establecer atributos en la solicitud para pasar al JSP de resultados
                content = "views/sueldo.jsp";

                request.setAttribute("content", content);
                request.setAttribute("dni", dni);
                request.setAttribute("sueldo", sueldo);

                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } else {
                // Enviar respuesta de error
                response.setContentType("text/plain");
                response.getWriter().write("DNI no proporcionado.");
            }
        } else if (opcion.equals("editarEmpleado")) {
            System.out.println("Usted ha presionado la opcion editar");
            request.setAttribute("content", "views/editarEmpleado.jsp");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request, response);
            return;

    }

}

    /**
     * Maneja las peticiones POST.
     *
     * @param request  La solicitud HTTP recibida.
     * @param response La respuesta HTTP que se enviará.
     * @throws ServletException Si ocurre un error durante el manejo de la solicitud.
     * @throws IOException      Si hay un error de entrada/salida al manejar la solicitud.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcion");

        if ("modificarEmpleado".equals(opcion)) {
            // Recolectar datos del formulario
            String nombre = request.getParameter("nombre");
            String dni = request.getParameter("dni");
            char sexo = request.getParameter("sexo").charAt(0);
            int categoria = Integer.parseInt(request.getParameter("categoria"));
            double anyos = Double.parseDouble(request.getParameter("anyos"));

            // Realizar las validaciones y cálculos necesarios

            // Crear el objeto Empleado con los datos recolectados
            Empleado empleado = null;
            try {
                empleado = new Empleado(nombre, dni, sexo, categoria, anyos);
            } catch (DatosNoCorrectosException e) {
                throw new RuntimeException(e);
            }

            // Calcular el sueldo
            Nomina nomina = new Nomina();
            double sueldo = nomina.sueldo(categoria, anyos);

            // Actualizar el sueldo en la base de datos
            try {
                NominaDAO nominaDAO = new NominaDAO();
                nominaDAO.actualizarSueldo(dni, sueldo);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Actualizar los datos del empleado en la base de datos
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            try {
                empleadoDAO.modificarEmpleado(empleado);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Redirigir a una página de confirmación
            String content = "views/empleadoguardado.jsp";
            request.setAttribute("content", content);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }else if ("eliminarEmpleado".equals(opcion)) {
            String dni = request.getParameter("dni");

            try {
                NominaDAO nominaDAO = new NominaDAO();
                nominaDAO.eliminarSueldo(dni);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            EmpleadoDAO empleadoDAO = new EmpleadoDAO();

            empleadoDAO.eliminarEmpleadoPorDNI(dni);

            String content = "views/empleadoborrado.jsp";

            request.setAttribute("content", content);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
    }




