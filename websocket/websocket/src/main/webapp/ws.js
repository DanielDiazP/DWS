/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var wsUri = "ws://localhost:8080/websocket/endpoint";
console.log("Connecting to " + wsUri);
var idToken;
var websocket;
var output = document.getElementById("salida");

function conectar(user,pass) {

    websocket = new WebSocket(wsUri + "/" + user + "/" + pass);

    websocket.onopen = function () {

        writeToScreen("Usuario "+ user +" conectado");
        if (user == "google")
        {
            websocket.send(idToken);
        }
    };
    
    websocket.onmessage = function (evt) {
        if (typeof evt.data == "string") {
            writeToScreen("Recibido(texto): " + evt.data);
        } else {
            writeToScreen("Recibido(binario): " + evt.data);
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
    chat.value=" ";
}



function writeToScreen(mensaje) {
//    var parrafo = document.createElement("p");
//    parrafo.innerHTML = mensaje;
//    output.appendChild(parrafo);
    areaTexto.value=mensaje;
}

function onSignIn(googleUser) {
    idToken = googleUser.getAuthResponse().id_token;
    user.value = "google";
    pass.value = "google";
}
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
    websocket.close();
}



if ($('input#guardar').is(':checked')) {

}
