<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Vista Única</title>
    <link rel="stylesheet" type="text/css" href="./css/style.css">

</head>
<body>
<%@include file="header/header.jsp"%>
<div class="content">
    <% if (request.getAttribute("content") == null || ((String) request.getAttribute("content")).isEmpty()) { %>

    <%@ include file="views/bienvenida.jsp" %>

    <% } else { %>
    <jsp:include page="${content}" />
    <% } %>
</div>
<%@include file="footer/footer.jsp"%>

</body>
</html>
