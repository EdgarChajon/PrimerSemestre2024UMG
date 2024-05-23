/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar Chajón
 */
public class CConexion {
   
private Connection connection;
Connection conectar= null;
    
    String usuario = "elojjtrw";
    String password = "hPb_iO-Niq70AMdjCDj1zKEF0mknXqCa";  
    String db = "elojjtrw";
    String ip = "bubble.db.elephantsql.com";
    String puerto = "5432";
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+db;
    
    public Connection establecer(){
        Connection conectar = null;
        try{
            Class.forName("org.postgresql.Driver");
            conectar=DriverManager.getConnection(cadena,usuario,password);
            System.out.println("Se conecto correctamente a la base de datos");
        }catch(Exception e){
            System.out.println("Error al conectar a la base de datos"+ e.toString());
        }
        return conectar;
    }
    

}
