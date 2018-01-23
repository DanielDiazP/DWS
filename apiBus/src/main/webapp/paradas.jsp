<%-- 
    Document   : infoLineas
    Created on : 19-ene-2018, 13:01:10
    Author     : dani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informacion de linea</title>
    </head>
    <body>
        <h2>Selecciona el codigo de la parada si quieres obtener informacion de ella</h2>
        <table border="1">
            <th>NOMBRE DE PARADA</th>
            <th>DIRECCION</th>
            <th>NUMERO DE PARADA</th>
                <c:forEach items="${paradasLinea}" var="parada">  
                <tr>
                    <td>${parada.name}</td>
                    <td>${parada.postalAddress}</td>
                    <td><a href="http://localhost:8080/apiBus/servletBus?opcion=getNodesLines&parada=${parada.stopId}">${parada.stopId}</a></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>
