/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.UsersDAO;
import model.Usuario;

/**
 *
 * @author Dani
 */
public class UsuariosServicios {

    public boolean comprobacionNombre(String nombre) {
        UsersDAO dao = new UsersDAO();
        return dao.existenciaNombre(nombre) == null;
    }

    public Usuario insertarUser(Usuario usuario) {
        UsersDAO dao = new UsersDAO();
        return dao.addUser(usuario);
    }

    public boolean comprobacionCodigo(String codigo) {
        UsersDAO dao = new UsersDAO();
        return dao.existenciaCodigo(codigo) != null;
    }

    public boolean activar(String codigo) {
        UsersDAO dao = new UsersDAO();
        return dao.activar(codigo) != null;
    }

    public boolean comprobacionPass(String nombre, String pass) {
        UsersDAO dao = new UsersDAO();
        return dao.comprobarPass(nombre, pass) != null;
    }

}
