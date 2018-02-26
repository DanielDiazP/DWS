/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import model.Canal;
import model.Usuario;
import dao.ChatDao;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.PasswordHash;

/**
 *
 * @author Dani
 */
public class ChatServicios {

    public boolean registroCorrecto(Usuario user) {
        boolean registro = false;
        try {
            ChatDao dao = new ChatDao();
            Usuario userBD = dao.datosUsuario(user);
            if (userBD != null) {
                boolean passCorrecta = PasswordHash.getInstance().validatePassword(user.getPass(), userBD.getPass());
                if (passCorrecta) {
                    registro = true;
                } else {
                    user.setPass(PasswordHash.getInstance().createHash(user.getPass()));
                    registro = registroNuevo(user);
                }
            } else {
                user.setPass(PasswordHash.getInstance().createHash(user.getPass()));
                registro = registroNuevo(user);
            }

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ChatServicios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(ChatServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registro;
    }

    public boolean registroNuevo(Usuario user) {
        ChatDao dao = new ChatDao();
        return dao.nuevoUsuario(user) > 0;
    }
}
