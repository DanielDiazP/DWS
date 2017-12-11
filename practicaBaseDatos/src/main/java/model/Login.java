/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dani
 */
public class Login {
    public Login(){
        
    }
    
    private String nombre;
    private String password;
    private String email;
    
    
    public String getNombre() {
        return nombre;
    }
     public String getPassword() {
        return password;
    }
      public String getEmail() {
        return email;
    }
     public void setNombre(String nombre) {
        this.nombre = nombre;
    }
      public void setPassword(String password) {
        this.password = password;
    }
      public void setEmail(String email) {
        this.email = email;
    }
     
    
}
