/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var wsUri = "ws://localhost:8080/websocket/endpoint";
console.log("Connecting to " + wsUri);
var idToken;
var websocket;
var user;
var canal = "global";



function conectar(email, password) {

    websocket = new WebSocket(wsUri + "/" + email + "/" + password, []);
    user = email;
    websocket.onopen = function () {
        if (email == "google@gmail.com") {

            var object = {
                "nombre_user": user,
                "tipo": "texto",
                "mensaje": idToken
            };

            websocket.send(JSON.stringify(object));
        }
        $('#text').css('display', 'block');
        $('#login').css('display', 'none');
        $('#logout').css('display', 'block');
        writeToScreen("Usuario " + email + " conectado");
    };

    websocket.onmessage = function (evt) {
        var texto = JSON.parse(evt.data);


        switch (texto.tipo) {
            case "texto":
                writeToScreen("Recibido: " + texto.mensaje);
                break;
            case "canales":
                $('#canales').css('display', 'block');
                for (var canal in texto.nombre) {
                    $("#canales").append(new Option(texto.nombre[canal], texto.nombre[canal]));
                }
                break;
        }

    };
    websocket.onerror = function (evt) {
        writeToScreen('ERROR: ' + evt.data);
    };
    websocket.onclose = function () {
        writeToScreen("Desconectado");
        $('#areaTexto').val('');
    };
}

function crearCanal() {
    var nombre = prompt("Nombre del canal a crear");
    var object = {
        "nombre_user": user,
        "tipo": "canal",
        "canalNuevo": nombre
    };
    websocket.send(JSON.stringify(object));
}

function getCanales() {
    var object = {
        "tipo": "canales"
    };

    websocket.send(JSON.stringify(object));

}

function enviarMensaje() {

    var texto = icon_prefix2.value;
    var fecha;
    if (document.getElementById("test5").checked) {
        fecha = Date.now();
    }

    writeToScreen("Enviado(canal " + canal + "): " + texto);
    var object = {
        "tipo": "texto",
        "mensaje": texto,
        "nombre_user": user,
        "canalActual": canal,
        "fecha": fecha
    };
    websocket.send(JSON.stringify(object));
    icon_prefix2.value = " ";
}

function cargarMensajes() {
    var fecha1 = document.getElementById("fecha1");
    var fecha2 = document.getElementById("fecha2");

    var object = {
        "fecha": fecha1.value,
        "fecha2": fecha2.value,
        "nombre_user": user
    };
    websocket.send(JSON.stringify(object));
}

function cambiarCanal(opcion) {
    canal = opcion;
}



function writeToScreen(mensaje) {
    $('#areaTexto').val($('#areaTexto').val() + "\n" + mensaje);
    $('#areaTexto').scrollTop($('#areaTexto')[0].scrollHeight);
}

function onSignIn(googleUser) {
    idToken = googleUser.getAuthResponse().id_token;
    email.value = "google@gmail.com";
    password.value = "google";
}
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
    $('#text').css('display', 'none');
    $('#login').css('display', 'block');
    $('#logout').css('display', 'none');
    websocket.close();
}


 