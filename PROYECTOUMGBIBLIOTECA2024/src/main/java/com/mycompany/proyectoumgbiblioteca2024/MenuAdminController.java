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
    private Button regresarMenuPrincipal;


    
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
      Stage currentStage = (Stage) agregarLibro.getScene().getWindow();
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
      Stage currentStage = (Stage) agregarLibro.getScene().getWindow();
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
      Stage currentStage = (Stage) agregarLibro.getScene().getWindow();
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
      Stage currentStage = (Stage) agregarLibro.getScene().getWindow();
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
      Stage currentStage = (Stage) agregarLibro.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }
        
        
     
        
    }

    @FXML
    private void regresarMenuPrincipal(ActionEvent event) {
    }
       
      
        
        
        
     
}
