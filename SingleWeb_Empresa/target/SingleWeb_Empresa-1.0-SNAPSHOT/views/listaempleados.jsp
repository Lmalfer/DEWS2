<!-- listaempleados.jsp -->
<%@ page import="model.Empleado" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<div class="menu-container">
    <h1>Listar Empleados</h1>
    <table border="1">
        <tr class="principal">
            <td>Nombre</td>
            <td>Dni</td>
            <td>Sexo</td>
            <td>Categoria</td>
            <td>Años</td>
            <td>Acciones</td> <!-- Columna para el botón de eliminación -->

        </tr>
        <%
            List<Empleado> lista = (List<Empleado>) request.getAttribute("lista");
            for (Empleado empleado : lista) {
        %>
        <tr>
            <td><%= empleado.getNombre() %></td>
            <td><%= empleado.getDni() %></td>
            <td><%= empleado.getSexo() %></td>
            <td><%= empleado.getCategoria() %></td>
            <td><%= empleado.getAnyos() %></td>
            <td>
                <!-- Formulario para enviar la solicitud de eliminación directamente a empleadoborrado.jsp -->
                <form action="gestor" method="post">
                    <input type="hidden" name="opcion" value="eliminarEmpleado">
                    <input type="hidden" name="dni" value="<%= empleado.getDni() %>">
                    <input type="submit" value="Eliminar">
                </form>

            </td>
        </tr>
        <% } %>
    </table>
</div>
