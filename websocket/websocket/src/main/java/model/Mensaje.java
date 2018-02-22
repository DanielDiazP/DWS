/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author Dani
 */
public class Mensaje {

    private long id;
    private String mensaje;
    private LocalDateTime fecha;
    private long id_canal;
    private String nombre_user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
//        public String getUser() {
//      
//    }

//    public void setUser(String user) {
//        this.user = user;
//    }

}
