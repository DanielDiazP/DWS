<%-- 
    Document   : inicio
    Created on : 19-ene-2018, 10:51:07
    Author     : dani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de lineas</title>
    </head>
    <body>
        <h1>Lineas</h1>
        <table border="1">
            <tr>
                <th>LINEA</th>
                <th>INICIO</th>
                <th>FINAL</th>
            </tr>
            <c:forEach items="${lineasBus}" var="linea">  
                <tr>
                    <td><a href="http://localhost:8080/apiBus/servletBus?opcion=getInfoLine&numeroLinea=${linea.line}">${linea.label}</a></td>
                    <td>${linea.nameA}</td>
                    <td>${linea.nameB}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
