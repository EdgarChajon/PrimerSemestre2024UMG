/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

/**
 *
 * @author Edgar Chajón
 */
public class SesionUsuario {
    

    
    private static SesionUsuario instancia;
    private String nombreUsuario;
    private String rolUsuario;
    private int id;

    public SesionUsuario() {
    }
    
    public static SesionUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int idUsuario) {
        this.id = idUsuario;
    }
    
    
    
    
    
}
