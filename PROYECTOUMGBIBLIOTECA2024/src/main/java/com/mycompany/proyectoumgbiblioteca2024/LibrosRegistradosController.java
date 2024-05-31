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
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class LibrosRegistradosController implements Initializable {
    @FXML
    private TableView<Libro> tvbooks;
    @FXML
    private TableColumn<Libro, Integer> COLISBN;
    @FXML
    private TableColumn<Libro, String> COLTItulo;
    @FXML
    private TableColumn<Libro, String> COLAutor;
    @FXML
    private TableColumn<Libro, Integer> ColAno;
    @FXML
    private TableColumn<Libro,String> COLEditorial;
    @FXML
    private TableColumn<Libro, String> COLGenero;
    @FXML
    private TableColumn<Libro, Integer> COLStock;
    @FXML
    private TableColumn<Libro, Timestamp> registroLibro;
    @FXML
    private Button VerLibrosRegistrados;
    @FXML
    private Button RegresaraMenuAdmin;
    
    private Connection conexionBD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       CConexion cConexion = new CConexion();
      conexionBD = cConexion.establecer();
        // TODO
        
     COLISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
     COLTItulo.setCellValueFactory(new PropertyValueFactory<>("tituloLibro"));
     COLAutor.setCellValueFactory(new PropertyValueFactory<>("autorLibro"));
     ColAno.setCellValueFactory(new PropertyValueFactory<>("anoPublicacion"));
     COLEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
     COLGenero.setCellValueFactory(new PropertyValueFactory<>("generoliterario"));
     COLStock.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));
     registroLibro.setCellValueFactory(new PropertyValueFactory<>("fecha_creacion"));
        
    }    

    @FXML
    private void VerLibrosRegistrados(ActionEvent event) {
        
        try {
        // Limpiar la tabla antes de cargar nuevos datos
        tvbooks.getItems().clear();
        
        // Crear la consulta SQL
        String sql = "SELECT * FROM registrolibro";
        
        // Ejecutar la consulta y obtener el resultado
        PreparedStatement statement = conexionBD.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        
        // Iterar sobre el resultado y agregar cada fila a la tabla
        while (resultSet.next()) {
            Libro libro = new Libro();
            
            
            libro.setISBN(resultSet.getLong("isbn"));
            libro.setTituloLibro(resultSet.getString("titulolibro"));
            libro.setAutorLibro(resultSet.getString("autorlibro"));
            libro.setAnoPublicacion(resultSet.getLong("anopublicacion"));
            libro.setEditorial(resultSet.getString("editorial"));
            libro.setGeneroliterario(resultSet.getString("generoliterario"));
            libro.setCantidadStock(resultSet.getInt("cantidadstock"));
            libro.setEstadolibro(resultSet.getBoolean("estadolibro"));
            libro.setFecha_creacion(resultSet.getTimestamp("fecha_creacion"));
            // Agregar el libro a la tabla
            tvbooks.getItems().add(libro);
        }
        
        // Cerrar el statement y el resultSet
        statement.close();
        resultSet.close();
        
    } catch (SQLException e) {
        System.out.println("Error al obtener libros registrados: " + e.getMessage());
    }
        
        
        
        
        
        
        
    }

    @FXML
    private void RegresaraMenuAdmin(ActionEvent event) {
        try {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAdmin.fxml"));
        Parent root = loader.load();

        
        Stage stage = (Stage) RegresaraMenuAdmin.getScene().getWindow();
        
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        mostrarAlerta("Error", "No se pudo cargar la vista del menú principal.");
        e.printStackTrace();
    }
        
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
