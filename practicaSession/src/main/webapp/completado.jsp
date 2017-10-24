<%-- 
    Document   : newjsp1
    Created on : 18-oct-2017, 10:30:41
    Author     : DAW
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> </title>
        <style>
            #completado{color: green}
            #continua{color: aqua}
        </style>

    </head>
    <body>
        <c:if test="${nivel==3}">
            <h1 id="completado">Reto conseguido!</h1>
        </c:if>
        <c:if test="${nivel!=3}">
            <h1 id="continua">Paso completado</h1>
        </c:if>
    </body>
</html>
