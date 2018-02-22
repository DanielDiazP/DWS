/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import utils.IdTokenVerifierAndParser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import servicios.ChatServicios;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import model.Usuario;

/**
 *
 * @author Dani
 *
 */
@ServerEndpoint("/endpoint/{user}/{pass}")
public class ServerEndPoint {

    @OnOpen
    public void onOpen(Session session, @PathParam("user") String user, @PathParam("pass") String pass) {
        session.getUserProperties().put("user", user);
        if (user.equals("google@gmail.com")) {
            session.getUserProperties().put("login", "NO");
        } else {
//            ChatServicios cS = new ChatServicios();
//            Usuario u = new Usuario();
//            u.setUser(user);
//            //hasear la pass falta
//            u.setPass(pass);
//            if (cS.registroCorrecto(u)) {
                session.getUserProperties().put("login", "OK");
//            } else {
//                try {
//                    session.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }

    }

    @OnMessage
    public void echoText(String mensaje, Session sessionQueManda) {
        if (!sessionQueManda.getUserProperties().get("login").equals("OK")) {
            try {
                //comprobacion del token de google
                String idToken = mensaje;
                GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
                String name = (String) payLoad.get("name");
                sessionQueManda.getUserProperties().put("user", name);
                System.out.println(payLoad.getJwtId());
                sessionQueManda.getUserProperties().put("login", "OK");
            } catch (Exception ex) {
                try {
                    sessionQueManda.close();
                } catch (IOException ex1) {
                    Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            for (Session sesion : sessionQueManda.getOpenSessions()) {
                try {
                    if (!sesion.equals(sessionQueManda)) {
                        sesion.getBasicRemote().sendText(sessionQueManda.getUserProperties().get("user") + " : " + mensaje);
                    }
                } catch (IOException e) {
                    Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

    }

    @OnClose
    public void onClose(Session session) {
        for (Session s : session.getOpenSessions()) {
            try {
                System.out.println(s.getUserProperties().get("user"));
                s.getBasicRemote().sendText("SALIDO " + session.getUserProperties().get("user").toString());
            } catch (IOException ex) {
                Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
