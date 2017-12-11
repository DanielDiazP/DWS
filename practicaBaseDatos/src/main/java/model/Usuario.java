/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Dani
 */
public class Usuario {

    public Usuario() {

    }

    private int id;
    private String nombre;
    private String password;
    private String codigoActivacion;
    private String email;
    private Boolean activado;
    private Date fechaActivacion;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getCodigoActivacion() {
        return codigoActivacion;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActivado() {
        return activado;
    }

    public Date getFechaActivacion() {
        return fechaActivacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCodigoActivacion(String codigo) {
        this.codigoActivacion = codigo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActivado(Boolean activado) {
        this.activado = activado;
    }

    public void setFechaActivacion(Date fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }
}
