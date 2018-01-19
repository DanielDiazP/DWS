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
            <c:forEach items="${lineasBus}" var="linea">  
                <tr>
                    <td>Nª de Linea ${linea.line}</td>
                    <td>Nombre de linea ${linea.label}</td>
                    <td>Inicio ${linea.nameA}</td>
                    <td>Destino ${linea.nameB}</td>
                    <td>Fecha inicio ${linea.dateFirst}</td>
                    <td>Fecha fin ${linea.dateEnd}</td>
                    <td><form action="servletBus?opcion=getInfoLine" name="formulario" method="POST">
                             <input type="hidden" name="numeroLinea" value="${linea.line}"/>
                            <input type="submit" value="Info"/>
                        </form></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
