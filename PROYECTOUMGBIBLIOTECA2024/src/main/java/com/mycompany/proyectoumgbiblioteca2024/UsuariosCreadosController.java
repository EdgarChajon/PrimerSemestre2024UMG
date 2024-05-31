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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Edgar Chajón
 */
public class UsuariosCreadosController implements Initializable {

    @FXML
    private TableView<Usuario> tvUsuario;
    @FXML
    private TableColumn<Usuario, String> COLNombre;
    @FXML
    private TableColumn<Usuario, Integer> COLID;
    @FXML
    private TableColumn<Usuario, Boolean> ColEstado;
    @FXML
    private TableColumn<Usuario, String> COLRol;
    @FXML
    private TableColumn<Usuario, String> COLCorreo;
    @FXML
    private TableColumn<Usuario, String> direccion;
    @FXML
    private Button VerTodosLosUsuarios;
    @FXML
    private Button RegresaMenuAdmin;
    
    private Connection conexionBD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CConexion cConexion = new CConexion();
        conexionBD = cConexion.establecer();
        
    COLID.setCellValueFactory(new PropertyValueFactory<>("id"));
    COLNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    ColEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    COLRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
    COLCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
    direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        
        // TODO
    }    

    @FXML
    private void VerTodosLosUsuarios(ActionEvent event) {
        
        try {
        // Limpiar la tabla antes de cargar nuevos datos
        tvUsuario.getItems().clear();
        
        // Crear la consulta SQL
        String sql = "SELECT * FROM UsuarioBiblioteca";
        
        // Ejecutar la consulta y obtener el resultado
        PreparedStatement statement = conexionBD.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        
        // Iterar sobre el resultado y agregar cada fila a la tabla
        while (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(resultSet.getInt("id"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setEstado(resultSet.getBoolean("estado"));
            
            
            usuario.setRolUsuario(resultSet.getString("rolusuario"));
            usuario.setCorreo(resultSet.getString("correo"));
            usuario.setDireccion(resultSet.getString("direccion"));
            // Agregar el usuario a la tabla
            tvUsuario.getItems().add(usuario);
        }
        
        // Cerrar el statement y el resultSet
        statement.close();
        resultSet.close();
        
    } catch (SQLException e) {
        System.out.println("Error al obtener usuarios: " + e.getMessage());
    }
    }

    @FXML
    private void RegresaMenuAdmin(ActionEvent event) {
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/MenuAdmin.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) RegresaMenuAdmin.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
    }
        }  
        
        
    
    
}
