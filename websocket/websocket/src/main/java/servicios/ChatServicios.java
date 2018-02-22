/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import model.Canal;
import model.Usuario;
import dao.ChatDao;

/**
 *
 * @author Dani
 */
public class ChatServicios {

    public boolean registroCorrecto(Usuario user) {
        ChatDao dao = new ChatDao();
        if (dao.datosUsuario(user) == null) {
            return registroNuevo(user);
        } else {
            return true;
        }
    }

    public boolean registroNuevo(Usuario user) {
        ChatDao dao = new ChatDao();
        return dao.nuevoUsuario(user) > 0;
    }
}
