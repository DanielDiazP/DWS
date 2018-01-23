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
        <script>
            function cargarParada(id, destino, nombre) {
                document.getElementById("idLinea").value = id;
                document.getElementById("nombreLinea").value = nombre;
                document.getElementById("destinoLinea").value = destino;
            }
        </script>
    </head>
    <body>
        <h1>Lineas</h1>
        <table border="1">
            <tr>
                <th>LINEA</th>
                <th>INICIO/FINAL</th>
            </tr>
            <c:forEach items="${lineasBus}" var="linea">  
                <tr>
                    <td>${linea.label}</td>
                    <td> 
                        <select id="parada" onchange="cargarParada(this.value, this.options[this.selectedIndex].innerHTML,${linea.label})">
                            <option disabled selected>Parada</option>
                            <option value="${linea.line}" name="${linea.nameA}">${linea.nameA}</option>
                            <option value="${linea.line}" name="${linea.nameB}">${linea.nameB}</option>
                        </select> 
                    </td>
                </tr>

            </c:forEach>
        </table>

        <h2>Selecciona la parada de destino de una linea para ver la lista de paradas</h2>

        <form action="servletBus?opcion=getStopsLine" name="formulario" method="POST">
            <input type="hidden" id="idLinea" name="numeroLinea">
            <input type="text" id="nombreLinea" name="nombreLinea">
            <input type="text" id="destinoLinea" name="direccion">
            <input type="submit" value="enviar">
        </form>
    </body>
</html>
