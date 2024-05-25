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
public class MenuUsuarioController implements Initializable {

    @FXML
    private Button realizarPrestamoLibro;
    @FXML
    private Button verHistorialPrestamo;
    @FXML
    private Button restablecerContra;
    @FXML
    private Button devolucionPrestamo;
    @FXML
    private Button verMultas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void realizarPrestamoLibro(ActionEvent event) {
    }

    @FXML
    private void verHistorialPrestamo(ActionEvent event) {
    }

    @FXML
    private void restablecerContra(ActionEvent event) {
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/RestablecerContraseña.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage)  restablecerContra.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }
    }

    @FXML
    private void devolucionPrestamo(ActionEvent event) {
    }

    @FXML
    private void verMultas(ActionEvent event) {
    }
    
}
