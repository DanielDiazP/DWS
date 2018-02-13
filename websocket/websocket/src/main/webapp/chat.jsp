<%-- 
    Document   : chat
    Created on : 12-feb-2018, 11:57:18
    Author     : Dani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id"
              content="951446828261-45oh8gn41o9c6ut9fqktki1tdalibqa3.apps.googleusercontent.com">
        <title>chat</title>

    </head>
    <body>
        <div class="g-signin2" data-onsuccess="onSignIn"></div>
        <a href="#" onclick="signOut();">Sign out</a>

        <div style="text-align: center;">
            <form action="" id="formularioUsuario"> 
                <h2>Usuario</h2>
                <input onclick="conectar();" value="enviar" type="button"> 
                <input id="user" value="usuario" type="text"><br>
                <input id="pass" value="password" type="text"><br>
            </form>
            <form action=""> 
                <h2>Chat</h2>
                <input onclick="enviarMensaje();" value="enviar" type="button"> 
                <input id="chat" value="Texto" type="text"><br>
            </form>
        </div>
        <div id="salida"></div>

        <script language="javascript" type="text/javascript" src="ws.js">
        </script>
       
    </body>
</html>