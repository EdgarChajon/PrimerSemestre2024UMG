/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

import java.security.SecureRandom;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Edgar Chajón
 */
public class UsuarioCrearCuentaController implements Initializable {

    @FXML
    private Button aceptarCrearUsuario;
    @FXML
    private Button regresarMenuInicio;
    @FXML
    private TextField nombre;
    @FXML
    private TextField direccion;
    @FXML
    private TextField telefono;
    @FXML
    private TextField cui;
    @FXML
    private PasswordField ContraUsuario;
    @FXML
    private TextField correo;
    
    private Connection conexionBD;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CConexion cConexion = new CConexion();
        conexionBD = cConexion.establecer();
        // TODO
    }    

    @FXML
    private void aceptarCrearUsuario(ActionEvent event) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre.getText());
        usuario.setDireccion(direccion.getText());
        usuario.setTelefono(Long.parseLong(telefono.getText()));
        usuario.setCui(Long.parseLong(cui.getText()));
        usuario.setRolUsuario("Usuario");
        usuario.setEstado(true);
        usuario.setContra(ContraUsuario.getText());
        usuario.setCorreo(correo.getText());
        
        insertarUsuario(usuario);
    }
    
    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuariobiblioteca (nombre, direccion, telefono, cui, estado, rolusuario, contra, correo, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getDireccion());
            statement.setLong(3, usuario.getTelefono());
            statement.setLong(4, usuario.getCui());
            statement.setBoolean(5, usuario.isEstado());
            statement.setString(6, usuario.getRolUsuario());
            statement.setString(7, usuario.getContra());
            statement.setString(8, usuario.getCorreo());
            statement.setTimestamp(9, usuario.getFechaCreacion());
            statement.executeUpdate();
            mostrarAlerta("Éxito", "Usuario registrado correctamente.");
            limpiarCampos();
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
            mostrarAlerta("Error", "No se pudo registrar el usuario.");
        }
    }
    
    private void limpiarCampos() {
        nombre.clear();
        direccion.clear();
        cui.clear();
        telefono.clear();
        //rolUsuario.clear();
        correo.clear();
        ContraUsuario.clear();
    }

    @FXML
    private void regresarMenuInicio(ActionEvent event) {
        
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/InicioInterfaz.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) regresarMenuInicio.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      
         }   
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
}
