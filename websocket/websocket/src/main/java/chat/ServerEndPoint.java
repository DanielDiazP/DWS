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
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import model.Mensaje;
import model.MessageDecoder;
import model.MessageEncoder;
import model.Usuario;
import servicios.PasswordServicios;

/**
 *
 * @author Dani
 *
 */
@ServerEndpoint(
        value = "/endpoint/{user}/{pass}",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class,
        configurator = ServletAwareConfig.class
)
public class ServerEndPoint {

    ChatServicios cS = new ChatServicios();
    Usuario u;
    HttpSession httpSession;

    @OnOpen
    public void onOpen(Session session, @PathParam("user") String user, @PathParam("pass") String pass, EndpointConfig config) throws InvalidKeySpecException {
        session.getUserProperties().put("user", user);
        httpSession = (HttpSession) config.getUserProperties().get("httpsession");
        if (user.equals("google@gmail.com")) {
            session.getUserProperties().put("login", "NO");
        } else {
            u = new Usuario();
            u.setNombre(user);
            u.setPass(pass);
            if (cS.registroCorrecto(u)) {
                session.getUserProperties().put("login", "OK");
            } else {
                try {
                    session.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        ArrayList<String> users = new ArrayList<>();
        for (Session s : session.getOpenSessions()) {
            users.add((String) s.getUserProperties().get("user"));
        }
        httpSession.setAttribute("conectados", users);
    }

    @OnMessage
    public void echoText(Mensaje mensaje, Session sessionQueManda) {
        if (!sessionQueManda.getUserProperties().get("login").equals("OK")) {
            try {
                //comprobacion del token de google
                String idToken = mensaje.getMensaje();
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
            if (null != mensaje.getFecha2()) {
                List<Mensaje> mensajesCargados;
                mensajesCargados = cS.cargarMensaje(mensaje);

                try {
                    sessionQueManda.getBasicRemote().sendObject(mensajesCargados);
                } catch (EncodeException | IOException ex) {
                    Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                if (mensaje.getTipo().equals("canales")) {
                    mensaje.setNombre(cS.getCanales());
                    try {
                        sessionQueManda.getBasicRemote().sendObject(mensaje);
                    } catch (EncodeException | IOException ex) {
                        Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {

                    if (mensaje.getFecha() != null) {
                        cS.guardarMensaje(mensaje);
                    }

                    if (mensaje.getTipo().equals("canal")) {
                        cS.nuevoCanal(mensaje);
                    }

                    for (Session sesion : sessionQueManda.getOpenSessions()) {
                        try {
                            if (!sesion.equals(sessionQueManda)) {
                                try {
                                    sesion.getBasicRemote().sendObject(mensaje);
                                } catch (EncodeException ex) {
                                    Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } catch (IOException e) {
                            Logger.getLogger(ServerEndPoint.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }

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
        ArrayList<String> users = new ArrayList<>();
        for (Session s : session.getOpenSessions()) {
            users.add((String) s.getUserProperties().get("user"));
        }
        httpSession.setAttribute("conectados", users);
    }

}
