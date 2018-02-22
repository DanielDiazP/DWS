/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var wsUri = "ws://localhost:8080/websocket/endpoint";
console.log("Connecting to " + wsUri);
var idToken;
var websocket;




function conectar(email,password) {

    websocket = new WebSocket(wsUri + "/" + email + "/" + password);

    websocket.onopen = function () {
        $('#text').css('display','block');
        $('#login').css('display','none');
        $('#logout').css('display','block');
          writeToScreen("Usuario "+ email +" conectado");
        if (email == "google@gmail.com"){
            websocket.send(idToken);
        }
       
    };
    
    websocket.onmessage = function (evt) {
        if (typeof evt.data == "string") {
            writeToScreen("Recibido(texto): " +evt.data);
        } else {
            writeToScreen("Recibido(texto): " +evt.data);
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




function enviarMensaje() {
    websocket.send(icon_prefix2.value);
    writeToScreen("Enviado: " + icon_prefix2.value);
    icon_prefix2.value=" ";
}



function writeToScreen(mensaje) {
$('#areaTexto').val($('#areaTexto').val()+"\n"+mensaje);
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
    $('#text').css('display','none');
    $('#login').css('display','block');
    $('#logout').css('display','none');
    websocket.close();
}



//if ($('input#guardar').is(':checked')) {
//
//}
  $('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15, // Creates a dropdown of 15 years to control year,
    today: 'Today',
    clear: 'Clear',
    close: 'Ok',
    closeOnSelect: false // Close upon selecting a date,
  });
