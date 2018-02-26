/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Dani
 */

public class Usuario {
    
    private String nombre;
    private String pass;

     
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String user) {
        this.nombre = user;
    }
     public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

//    public ArrayList<String> getCanal() {
//        return canales;
//    }
//
//    public void setCanal(ArrayList<String> canal) {
//        this.canales = canal;
//    }
//    
//    public boolean buscaCanal(String canal)
//    {
//        return this.canales.contains(canal);
//    }
    
}
