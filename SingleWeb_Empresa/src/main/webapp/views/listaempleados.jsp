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

        </tr>
        <% } %>
    </table>
</div>
