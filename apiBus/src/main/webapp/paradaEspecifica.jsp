<%-- 
    Document   : paradaEspecifica
    Created on : 23-ene-2018, 10:24:50
    Author     : dani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informacion de parada</title>
        <script>
            function funcion(a){
                var regExp=/[,]+[0-9]{1,3}+[\/]/g;
                return a.match(regExp);
            }
        </script>
    </head>
    <body>
        <h3>Parada ${resultadoParadas.name} Nº ${resultadoParadas.node} </h3>
        <h4>Lineas <script>funcion(${resultadoParadas.lines});</script></h4>
    </body>
</html>
