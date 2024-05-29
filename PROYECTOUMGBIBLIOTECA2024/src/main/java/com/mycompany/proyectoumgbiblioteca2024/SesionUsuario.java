/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Edgar Chajón
 */
public class SesionUsuario {
    

    
    private static SesionUsuario instancia;
    private String nombreUsuario;
    private String rolUsuario;
    private int id;
    private final String PROPERTIES_FILE = "sesion.properties";

   private SesionUsuario() {
        cargarSesionGuardada();
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
        guardarSesion();
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
        guardarSesion();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        guardarSesion();
    }
    
    private void cargarSesionGuardada() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
            nombreUsuario = properties.getProperty("nombreUsuario", "");
            rolUsuario = properties.getProperty("rolUsuario", "");
            id = Integer.parseInt(properties.getProperty("id", "0"));
        } catch (IOException | NumberFormatException e) {
            // Manejar la excepción, en caso de que el archivo de propiedades no exista o sea inválido
            e.printStackTrace();
        }
    }
    
    private void guardarSesion() {
        Properties properties = new Properties();
        try (OutputStream output = new FileOutputStream(PROPERTIES_FILE)) {
            properties.setProperty("nombreUsuario", nombreUsuario != null ? nombreUsuario : "");
            properties.setProperty("rolUsuario", rolUsuario != null ? rolUsuario : "");
            properties.setProperty("id", String.valueOf(id));
            properties.store(output, null);
        } catch (IOException e) {
            // Manejar la excepción en caso de error al guardar las propiedades
            e.printStackTrace();
        }
    }
    
}
