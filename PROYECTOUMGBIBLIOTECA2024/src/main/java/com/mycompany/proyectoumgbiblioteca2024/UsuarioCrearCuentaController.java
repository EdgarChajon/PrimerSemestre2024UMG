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
        String sqlInsert = "INSERT INTO usuariobiblioteca (nombre, direccion, telefono, cui, estado, rolusuario, contra, correo, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String sqlSelectId = "SELECT id FROM usuariobiblioteca WHERE nombre = ? AND cui = ? ORDER BY fecha_creacion DESC LIMIT 1";
    
    try (PreparedStatement statementInsert = conexionBD.prepareStatement(sqlInsert);
         PreparedStatement statementSelectId = conexionBD.prepareStatement(sqlSelectId)) {
        
        // Realizar la inserción del usuario
        statementInsert.setString(1, usuario.getNombre());
        statementInsert.setString(2, usuario.getDireccion());
        statementInsert.setLong(3, usuario.getTelefono());
        statementInsert.setLong(4, usuario.getCui());
        statementInsert.setBoolean(5, usuario.isEstado());
        statementInsert.setString(6, usuario.getRolUsuario());
        statementInsert.setString(7, usuario.getContra());
        statementInsert.setString(8, usuario.getCorreo());
        statementInsert.setTimestamp(9, usuario.getFechaCreacion());
        statementInsert.executeUpdate();
        
        // Obtener el ID del usuario recién insertado
        statementSelectId.setString(1, usuario.getNombre());
        statementSelectId.setLong(2, usuario.getCui());
        ResultSet resultSet = statementSelectId.executeQuery();
        
        if (resultSet.next()) {
            int idGenerado = resultSet.getInt("id");
            mostrarAlerta("Éxito", "Usuario registrado correctamente. ID del usuario: " + idGenerado +"Por favor anotar numero para ingresar al sistema");
        } else {
            mostrarAlerta("Éxito", "Usuario registrado correctamente, pero no se pudo obtener el ID.");
        }
        
        resultSet.close();
        limpiarCampos();
    } catch (SQLException e) {
        System.out.println("Error al insertar usuario: " + e.getMessage());
        mostrarAlerta("Error", "No se pudo registrar el usuario.");
    }
        limpiarCampos();
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
