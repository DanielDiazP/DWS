/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

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
        if (!user.equals("google")) {
            session.getUserProperties().put("login",
                    "OK");
        } else {
            session.getUserProperties().put("login",
                    "NO");
        }

    }

    @OnMessage
    public void echoText(String mensaje, Session sessionQueManda) {
        if (!sessionQueManda.getUserProperties().get("login").equals("OK")) {
            try {
                // comprobar login
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

            for (Session s : sessionQueManda.getOpenSessions()) {
                try {
                    if (!s.equals(sessionQueManda)) {
                        s.getBasicRemote().sendText(sessionQueManda.getUserProperties().get("user") + " : " + mensaje);
                    }
                } catch (IOException e) {
                    Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

    }

}
