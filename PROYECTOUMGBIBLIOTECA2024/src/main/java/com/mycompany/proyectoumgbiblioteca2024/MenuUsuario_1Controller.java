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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Edgar Chajón
 */
public class MenuUsuario_1Controller implements Initializable {

    @FXML
    private TextField IdUsuario;
    @FXML
    private TextField nombre;
    @FXML
    private Button RealizarPrestamoLibro;
    @FXML
    private Button verHistorialdePrestamos;
    @FXML
    private Button VerHistorialMultas;
    @FXML
    private Button RestablecerContraseña;
    @FXML
    private Button DevolucionLibros;
    @FXML
    private Button SalirDeSistema;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    cargarDatosUsuario();

        // TODO
    }   
    
     private void cargarDatosUsuario() {
        SesionUsuario sesion = SesionUsuario.getInstancia();
        IdUsuario.setText(String.valueOf(sesion.getId()));
        nombre.setText(sesion.getNombreUsuario());
    }
    
    



    @FXML
    private void RealizarPrestamoLibro(ActionEvent event) {
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/PrestamoLibro_1.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) RealizarPrestamoLibro.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }
        
    }

    @FXML
    private void verHistorialdePrestamos(ActionEvent event) {
    }

    @FXML
    private void RestablecerContraseña(ActionEvent event) {
    }

    @FXML
    private void DevolucionLibros(ActionEvent event) {
    }

    @FXML
    private void SalirDeSistema(ActionEvent event) {
    }
    
}
