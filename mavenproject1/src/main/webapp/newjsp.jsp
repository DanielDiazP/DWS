<%-- 
    Document   : newjsp
    Created on : 09-oct-2017, 9:50:09
    Author     : DAW
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practica 1</title>
    </head>
    <body>

        <%
            Map<String, String[]> parametros = request.getParameterMap();
            for (String parametro : parametros.keySet()) {

                String[] valores = parametros.get(parametro);

        %>
        <h1>
            <p style="color:<%out.print(parametros.keySet());%>"><%
                out.print("=" + parametros.get(parametro));
                %></p>
        </h1>
        <%}
        %>
    </body>
</html>
