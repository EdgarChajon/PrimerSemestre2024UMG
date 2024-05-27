/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

 package com.mycompany.proyectoumgbiblioteca2024;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class InicioInterfazController implements Initializable {
    
      @FXML
    private TextField nombreUsuario;
    @FXML
    private PasswordField contraUsuario;
    @FXML
    private Button ingresarCuenta;
    
    private Connection conectar;
    @FXML
    private Button restablecerContraUsuario;
    @FXML
    private Button crearCuentaUsuario;

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        CConexion conexion = new CConexion();
        conectar = conexion.establecer();
    }

    @FXML
    protected void ingresarCuenta(ActionEvent event) {
        String nombre = nombreUsuario.getText();
        String contra = contraUsuario.getText();

        try {
            String sql = "SELECT * FROM UsuarioBiblioteca WHERE nombre = ? AND Contra = ?";
            PreparedStatement pstmt = conectar.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, contra);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                SesionUsuario sesion = SesionUsuario.getInstancia();
                sesion.setNombreUsuario(rs.getString("nombre"));
                sesion.setRolUsuario(rs.getString("RolUsuario"));
                sesion.setId(rs.getInt("id"));

                String rolUsuario = rs.getString("RolUsuario");
                
                
              
                if (rolUsuario.equals("Admin")) {
                    cargarMenuAdmin();
                } else if (rolUsuario.equals("Usuario")) {
                    cargarMenuUsuario();
                } else {
                    mostrarMensaje("Rol de usuario no reconocido");
                }
            } else {
                mostrarMensaje("Nombre de usuario o contraseña incorrectos");
            }

            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            mostrarMensaje("Error al intentar iniciar sesión");
        }
    }

    private void cargarMenuAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/MenuAdmin.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
            // Cerrar la ventana actual de inicio de sesión
            Stage currentStage = (Stage) ingresarCuenta.getScene().getWindow();
            currentStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            mostrarMensaje("Error al cargar el menú de administrador");
        }
    }
    
    private void cargarMenuUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/MenuUsuario.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            
            // Cerrar la ventana actual de inicio de sesión
            Stage currentStage = (Stage) ingresarCuenta.getScene().getWindow();
            currentStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            mostrarMensaje("Error al cargar el menú de usuario");
        }
    }
    
    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void restablecerContraUsuario(ActionEvent event) {
        
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/RestablecerContraseña.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) restablecerContraUsuario.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }
        
        
    }

    @FXML
    private void crearCuentaUsuario(ActionEvent event) {
       
         try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/UsuarioCrearCuenta.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) crearCuentaUsuario.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }
        
        
     }

    
        
        
    
}