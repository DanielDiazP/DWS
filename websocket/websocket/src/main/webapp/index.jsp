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

        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id"
              content="951446828261-45oh8gn41o9c6ut9fqktki1tdalibqa3.apps.googleusercontent.com">
        <title>chat</title>
        <style>
            #imageURLId{
                font-size: 14px;
                font-weight: normal;
                resize: none;
                overflow-y: scroll;
            }

            #areaTexto
            {
                height: 100px;
                max-height: 100px;  
            }
            #login{
                height: 200px;
                width: 300px;
                text-align: center;
                margin-left: 38%;
            }
            #enviar{
                width: 150px;
                margin-top: -27px;           }
            #text{
                margin-top: 4%;
                height: 500px;
                width: 60%;
                margin-left: 18%;
                text-align: center;
            }
            #loginGoogle{
                display: inline-block;
                margin-left: 5%;

            }
            #logout{
                display: none;
                margin-left: 45%;
                margin-top: 2%;
                font-size: xx-large;
            }


            #guardar{
                margin-left: 2%;
                display: inline-block;
            }
            #text{
                display: none;
            }
            #botonCarga{
                margin-left: 3%;
            }
            #izquierda{
                width: 30%;
            }


        </style>

    </head>
    <body onbeforeunload="signOut();">


        <div id="login">
            <div id="formularioUsuario"> 
                <h3>Registrarse</h3>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email" type="email" class="validate">
                        <label for="email">Email</label>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="password" type="password" class="validate">
                            <label for="password">Password</label>
                        </div>
                    </div>
                </div>
                <input onclick="conectar(email.value, password.value);" class="btn waves-effect waves-light" id="enviar" type="button" name="action" value="Entrar">
               
                <div id="loginGoogle">
                    <div class="g-signin2" data-onsuccess="onSignIn"></div>
                </div>
            </div>
        </div>

        <div id="logout"> <a href="#" onclick="signOut();" >Sign out</a></div>
        <div id="izquierda">
            <input onclick="getCanales();" value="Canales" type="button"> 
            <select id="canales">

            </select>
        </div>
        <div id="text">
            <h3>Chat</h3>
            <div class="row">
                <textarea rows="40" cols="50" id="areaTexto"
                          style="resize: none;" readonly></textarea>
                <div class="col s12">
                    <div class="row">
                        <div class="input-field col s12" >
                            <i class="material-icons prefix">mode_edit</i>
                            <textarea id="icon_prefix2" class="materialize-textarea"></textarea>
                            <label for="icon_prefix2">Mensaje</label>
                        </div>
                    </div>
                    <input onclick="enviarMensaje();" id="botonTexto" class="btn waves-effect waves-light" type="button" name="action" value="Enviar">
                   
                    <p id="guardar">
                        <input type="checkbox" id="test5" />
                        <label for="test5">Guardar Mensajes</label>
                    </p>
                    <br>
                    <input type="date" id="fecha1" style="width: 15%;display: inline-block; margin-top: 5%;">
                    <input type="date" id="fecha2" style="width: 15%;display: inline-block;margin-left: 5%; margin-top: 5%;">
                    <input onclick="cargarMensajes();" id="botonCarga" class="btn waves-effect waves-light" type="button" name="action" value="Cargar mensajes">

                    

                </div>
            </div>
        </div>




        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

        <script language="javascript" type="text/javascript" src="ws.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
    </body>
</html>