<%-- 
    Document   : loggin
    Created on : 12-dic-2017, 22:44:38
    Author     : Dani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function boton(num) {
                var opcion = null;
                switch (num) {
                    case 1:
                        opcion = "login?opcion=login";
                        document.forms.formulario1.action = opcion;
                        document.forms.formulario1.submit();
                        break;
                    case 2:
                        opcion = "login?opcion=registrar";
                        document.forms.formulario2.action = opcion;
                        document.forms.formulario2.submit();
                        break;
                    case 3:
                        opcion = "login?opcion=unLogin";
                        document.forms.formulario3.action = opcion;
                        document.forms.formulario3.submit();
                        break;
                }
            }

        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="/login" name="formulario1" method="POST" >
            <input type="text" id="nombre1" name="nombre" size="12" />
            <input type="text" id="pass1" name="password" size="12"/>
            <input type="button" value="Logearse" onclick="boton(1);"/>
        </form>
        <form action="/login" name="formulario2" method="POST" >
            <input type="text" id="nombre2" name="nombre" size="12" />
            <input type="text" id="pass2" name="password" size="12"/>
            <input type="text" id="email" name="email" size="12" />
            <input type="button" value="Registrarse" onclick="boton(2);"/>
        </form>
        <form action="/login" name="formulario3" method="POST" >
            <input type="button" value="Salir" onclick="boton(3);"/>
        </form>
    </body>
</html>
