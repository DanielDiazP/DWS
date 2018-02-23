/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var wsUri = "ws://localhost:8080/websocket/endpoint";
console.log("Connecting to " + wsUri);
var idToken;
var websocket;
var iterationCount = 1000;
var keySize = 128;
var aesUtil = new AesUtil(keySize, iterationCount);
var user;
var canal = 0;


function conectar(email, password) {

    websocket = new WebSocket(wsUri + "/" + email + "/" + password);
    user = email;
    websocket.onopen = function () {
        if (email == "google@gmail.com") {

            var object = {
                "user": user,
                "tipo": "texto",
                "contenido": idToken
            };

            websocket.send(JSON.stringify(object));
        }
        $('#text').css('display', 'block');
        $('#login').css('display', 'none');
        $('#logout').css('display', 'block');
        writeToScreen("Usuario " + email + " conectado");
    };

    websocket.onmessage = function (evt) {
        var mensaje = JSON.parse(evt.data);
        var texto = aesUtil.decrypt(mensaje.salt, mensaje.iv, mensaje.key, mensaje.contenido);

        switch (mensaje.tipo) {
            case "texto":
                writeToScreen("Recibido: " + texto);
                break;
            case "canales":
                var canales = JSON.parse(texto);
                for (var canal in canales) {
                    $("#canales").append(new Option(canales[canal], canales[canal]));
                }
                writeToScreen("Recibido: " + texto);

                break;
        }
        writeToScreen("Recibido: " + texto);

    };
    websocket.onerror = function (evt) {
        writeToScreen('ERROR: ' + evt.data);
    };
    websocket.onclose = function () {
        writeToScreen("Desconectado");
        $('#areaTexto').val('');
    };
}


function getCanales() {
    var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
    var salt = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);

    var passphrase = "temp";

    var object = {

        "tipo": "canales",
        "contenido": "",
        "key": passphrase,
        "salt": salt,
        "iv": iv
    };


    websocket.send(JSON.stringify(object));

}

function enviarMensaje(canal) {
    var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
    var salt = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
    var passphrase = "temp";
    var texto = aesUtil.encrypt(salt, iv, passphrase, icon_prefix2.value);

    writeToScreen("Enviado(canal " + canal + "): " + aesUtil.decrypt(salt, iv, passphrase, texto));
    var object = {
        "tipo": "texto",
        "contenido": texto,
        "key": passphrase,
        "iv": iv,
        "salt": salt,
        "user": user,
        "canal": canal,
        "fecha": fecha
    };
    websocket.send(JSON.stringify(object));
    icon_prefix2.value = " ";
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



//if ($('input#guardar').is(':checked')) {
//
//}
$('.datepicker').datepicker({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15, // Creates a dropdown of 15 years to control year,
    today: 'Today',
    clear: 'Clear',
    close: 'Ok',
    closeOnSelect: false // Close upon selecting a date,
});
 