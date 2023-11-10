<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<div class="standard">
    <h2>Resultado</h2>
</div>
<%
    String dni = (String) request.getAttribute("dni");
    double sueldo = (Double) request.getAttribute("sueldo");
%>
<div class="styled-div">
    <div class="rowlistar">
        <%
            if (sueldo > 0) {
        %>
        <div class="list">DNI del empleado: <b><%= dni %>
        </b> - Sueldo: <b><%= String.format("%.0f", sueldo) %> euros
        </b></div>
    </div>
    <%
    } else {
    %>
    <div class="list">
        <p>No se ha encontrado ningún sueldo por el DNI <%= dni %>.</p>
    </div>
    <%
        }
    %>
</div>