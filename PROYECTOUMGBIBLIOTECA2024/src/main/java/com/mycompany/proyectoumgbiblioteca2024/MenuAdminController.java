/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Edgar Chajón
 */
public class MenuAdminController implements Initializable {

    @FXML
    private Button agregarUsuario;
    @FXML
    private Button editarUsuario;
    @FXML
    private Button eliminarUsuario;
    @FXML
    private Button agregarLibro;
    @FXML
    private Button editarLibro;
    @FXML
    private Button eliminarLibro;
    
    private Stage stage;
    @FXML
    private Button UsuariosCreados;
    @FXML
    private Button LibrosCreados;
    @FXML
    private Button SalirdeSistema;
    @FXML
    private Button AceptarDevolucionLibro;
    @FXML
    private Button LibrosPrestados;


    
     public void setStage(Stage stage) {
        this.stage = stage;
    }
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
     


// TODO
    }
    @FXML
    private void agregarLibro(ActionEvent event){
      try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/RegistroLibro.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) agregarLibro.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }
        
        
     }

    @FXML
    private void agregarUsuario(ActionEvent event) {
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/CrearUsuario.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) agregarUsuario.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }
    }

    @FXML
    private void editarUsuario(ActionEvent event) {
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/EditarUsuario.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) editarUsuario.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }
    }

    @FXML
    private void eliminarUsuario(ActionEvent event) {
     try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/EliminarUsuario.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) eliminarUsuario.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }   
        
    }

    @FXML
    private void editarLibro(ActionEvent event) {
        
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/EditarLibro.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) editarLibro.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }
    }

    @FXML
    private void eliminarLibro(ActionEvent event) {
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/EliminarLibro.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) eliminarLibro.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }
        
        
     
        
    }

    @FXML
    private void UsuariosCreados(ActionEvent event) {
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/UsuariosCreados.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) UsuariosCreados.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
    } 
        
        
        
    }

    @FXML
    private void LibrosCreados(ActionEvent event) {
       try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/LibrosRegistrados.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) LibrosCreados.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
    }   
        
        
        
        
    }

    @FXML
    private void SalirdeSistema(ActionEvent event) {
       try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/InicioInterfaz.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) SalirdeSistema.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
    } 
        
        
    }

    @FXML
    private void AceptarDevolucionLibro(ActionEvent event) {
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/AceptarDevolucionLibro.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) AceptarDevolucionLibro.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
    }
        }

    @FXML
    private void LibrosPrestados(ActionEvent event) {
       try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/HistorialAdmin.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) LibrosPrestados.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
    }
        }  
        
    
       
      
        
        
        
     
}
