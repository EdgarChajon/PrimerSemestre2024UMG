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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Edgar Chajón
 */
public class RestablecerContraseñaController implements Initializable {

    @FXML
    private TextField correo;
    @FXML
    private Button busquedaCorreo;
    @FXML
    private Button regresarMenuInicio;
    @FXML
    private PasswordField nuevaContra;
    
    private Connection conexionBD;
    @FXML
    private Button AceptarCambioContra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CConexion cConexion = new CConexion();
        conexionBD = cConexion.establecer();
        
        
        nuevaContra.setDisable(true);
        AceptarCambioContra.setDisable(true);
        
        // TODO
    }    

    @FXML
    private void busquedaCorreo(ActionEvent event) {
        String correoUsuario = correo.getText();
        if (correoUsuario.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese un correo electrónico.");
            return;
        }

        String sql = "SELECT * FROM usuariobiblioteca WHERE correo = ?";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            statement.setString(1, correoUsuario);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                mostrarAlerta("Éxito", "Usuario Registrado. Puede cambiar su contraseña.");
                nuevaContra.setDisable(false);
                AceptarCambioContra.setDisable(false);
            } else {
                mostrarAlerta("Error", "El correo ingresado no está registrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el correo: " + e.getMessage());
            mostrarAlerta("Error", "No se pudo realizar la búsqueda.");
        }
        }

    @FXML
    private void nuevaContra(ActionEvent event) {
        String correoUsuario = correo.getText();
        String nuevaContraseña = nuevaContra.getText();
        if (nuevaContraseña.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese una nueva contraseña.");
            return;
        }

        String sql = "UPDATE usuariobiblioteca SET contra = ? WHERE correo = ?";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            statement.setString(1, nuevaContraseña);
            statement.setString(2, correoUsuario);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                mostrarAlerta("Éxito", "Contraseña actualizada correctamente.");
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No se pudo actualizar la contraseña.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la contraseña: " + e.getMessage());
            mostrarAlerta("Error", "No se pudo actualizar la contraseña.");
        }
        
    }
    
    
    private void limpiarCampos() {
        correo.clear();
        nuevaContra.clear();
        nuevaContra.setDisable(true);
        AceptarCambioContra.setDisable(true);
    }
    
    
    
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void AceptarCambioContra(ActionEvent event) {
        String correoUsuario = correo.getText();
        String nuevaContraseña = nuevaContra.getText();
        if (nuevaContraseña.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese una nueva contraseña.");
            return;
        }

        String sql = "UPDATE usuariobiblioteca SET contra = ? WHERE correo = ?";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            statement.setString(1, nuevaContraseña);
            statement.setString(2, correoUsuario);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                mostrarAlerta("Éxito", "Contraseña actualizada correctamente.");
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No se pudo actualizar la contraseña.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la contraseña: " + e.getMessage());
            mostrarAlerta("Error", "No se pudo actualizar la contraseña.");
        }
    }

    @FXML
    private void regresarMenuInicio(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/InicioInterfaz.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            Stage currentStage = (Stage) regresarMenuInicio.getScene().getWindow();
            currentStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error al cargar la ventana InicioInterfaz");
        }
    }
    
}
