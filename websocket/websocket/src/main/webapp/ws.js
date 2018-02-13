/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var wsUri = "ws://localhost:8080/websocket/endpoint";
console.log("Connecting to " + wsUri);
var idToken;
var output = document.getElementById("salida");

function conectar() {
    

    var websocket = new WebSocket(wsUri, +"/" + user.value + "/" + pass.value, []);

    websocket.onopen = function () {
        
        writeToScreen("Conectado");
        if (user.value == "google")
        {
            websocket.send(idToken);
        }
    };
    websocket.onmessage = function (evt) {
        if (typeof evt.data == "string") {
            writeToScreen("Recibido: " + evt.data);
        } else {
            writeToScreen("Recibido: " + evt.data);
        }
    };
    websocket.onerror = function (evt) {
        writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
    };
    websocket.onclose = function () {
        writeToScreen("Desconectado");
    };
}




function enviarMensaje() {
    websocket.send(chat.value);
    writeToScreen("Enviado: " + chat.value);
}



function writeToScreen(mensaje) {
    var parrafo = document.createElement("p");
    parrafo.innerHTML = mensaje;
    output.appendChild(parrafo);
}

function onSignIn(googleUser) {
    idToken = googleUser.getAuthResponse().id_token;
    user.value = "google";
    pass.value = "google";
    conectar();
}
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
    websocket.close();
}

