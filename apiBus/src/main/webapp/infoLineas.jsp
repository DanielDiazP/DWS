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
        <title>Informacion</title>
    </head>
    <body>
        
        <table border="1">
           
                <tr>
                    <td>Fecha->${infoLineasBus.get(date)}</td>
                    <td>Linea->${infoLineasBus.get(lineId)}</td>
                    <td>Nombre->${infoLineasBus.get(label)}</td>
                    <td>Incidencias->${infoLineasBus.get(incidents)}</td>
                    <td>Inicio->${infoLineasBus.get(headerA)}</td>
                    <td>Destino->${infoLineasBus.get(headerB)}</td>
                </tr>
            
        </table>
    </body>
</html>
