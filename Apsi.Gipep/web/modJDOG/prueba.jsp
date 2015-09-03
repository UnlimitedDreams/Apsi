<%-- 
    Document   : prueba
    Created on : 25-ago-2015, 19:44:59
    Author     : usuario
--%>

<%@page import="Entity.Proyectos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%Proyectos miProyecto = (Proyectos) session.getAttribute("codProyecto"); %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=miProyecto.getCodigoProyecto()%></h1>
    </body>
</html>
