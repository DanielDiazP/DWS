/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dani
 */
public class Mensaje {
    
    private long id;

    private String mensaje;

    private String nombre_user;

    private long id_canal;

    private Date fecha;
    
    private Date fecha2;
    
    private String tipo;
    
    private String canalActual;
    
    private String canalNuevo;//nombre del canal para crear
    
    private ArrayList<String> nombre;//lista de nombres de los canales

      public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }  
    
    public String getNombre_user() {
        return nombre_user;
    }

    public void setNombre_user(String user) {
        this.nombre_user = user;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String contenido) {
        this.mensaje = contenido;
    }

    public long getId_canal() {
        return id_canal;
    }

    public void setId_canal(long canal) {
        this.id_canal = canal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
     public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha) {
        this.fecha2 = fecha;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }  
    
    public String getCanalNuevo() {
        return canalNuevo;
    }

    public void setCanalNuevo(String canal) {
        this.canalNuevo = canal;
    }
    
      public List<String> getNombre() {
        return nombre;
    }

    public void setNombre(ArrayList<String> lista) {
        this.nombre = lista;
    }  
    public String getCanalActual() {
        return canalActual;
    }

    public void setCanalActual(String canal) {
        this.canalActual = canal;
    }

}
