/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

import java.sql.Timestamp;

/**
 *
 * @author Edgar Chajón
 */
public class Usuario {
    
    private int id;
    private String nombre;
    private String direccion;
    private long telefono;
    private long cui;
    private boolean estado;
    private String rolUsuario;
    private String contra;
    private String correo;
    private Timestamp fechaCreacion ;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String direccion,long telefono, long cui, boolean estado, String rolUsuario, String contra,String correo, Timestamp fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cui = cui;
        this.estado = estado;
        this.rolUsuario = rolUsuario;
        this.contra = contra;
        this.correo=correo;
        this.fechaCreacion = fechaCreacion;
        this.telefono=telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getCui() {
        return cui;
    }

    public void setCui(long cui) {
        this.cui = cui;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    
    
}
