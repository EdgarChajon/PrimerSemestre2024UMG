/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
public class RegistroLibroController implements Initializable {

    @FXML
    private TextField ISBN;
    @FXML
    private TextField tituloLibro;
    @FXML
    private TextField autorLibro;
    @FXML
    private TextField anoPublicacion;
    @FXML
    private TextField editorial;
    @FXML
    private TextField generoLiterario;
    @FXML
    private TextField StockLibro;
    private Button Registrar;
    
    private boolean estadoLibro;
    
    private Connection conexionBD;
    @FXML
    private Button RegistrarLibro;
    @FXML
    private Button CancelarRegistroLibro;
    @FXML
    private Button regresarMenuAdmin;
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
    
    
    public RegistroLibroController(){
        
    }
    

    

   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      CConexion cConexion = new CConexion();
      conexionBD = cConexion.establecer();
      configurarTabla();
      cargarDatosEnTabla();


      }

      public void configurarTabla(){
        COLISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        COLTItulo.setCellValueFactory(new PropertyValueFactory<>("tituloLibro"));
        COLAutor.setCellValueFactory(new PropertyValueFactory<>("autorLibro"));
        ColAno.setCellValueFactory(new PropertyValueFactory<>("anoPublicacion"));
        COLEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        COLGenero.setCellValueFactory(new PropertyValueFactory<>("generoliterario"));
        COLStock.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));
        registroLibro.setCellValueFactory(new PropertyValueFactory<>("fecha_creacion"));




 }


private void cargarDatosEnTabla() {
        tvbooks.setItems(FXCollections.observableArrayList(obtenerTodosLosLibros()));
    }

@FXML
    private void RegistrarLibro(ActionEvent event) {
        Libro libro = new Libro();
        libro.setISBN(Long.parseLong(ISBN.getText()));
        libro.setTituloLibro(tituloLibro.getText());
        libro.setAutorLibro(autorLibro.getText());
        libro.setAnoPublicacion(Long.parseLong(anoPublicacion.getText()));
        libro.setEditorial(editorial.getText());
        libro.setGeneroliterario(generoLiterario.getText());
        libro.setCantidadStock(Integer.parseInt(StockLibro.getText()));
        libro.setEstadolibro(true);
        libro.setFecha_creacion(Timestamp.valueOf(LocalDateTime.now()));
        insertarLibro(libro);
        cargarDatosEnTabla();
    }

    public void insertarLibro(Libro libro) {
        String sql = "INSERT INTO registrolibro (isbn, titulolibro, autorlibro, anopublicacion, editorial, generoliterario, cantidadstock, estadolibro, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            statement.setLong(1, libro.getISBN());
            statement.setString(2, libro.getTituloLibro());
            statement.setString(3, libro.getAutorLibro());
            statement.setLong(4, libro.getAnoPublicacion());
            statement.setString(5, libro.getEditorial());
            statement.setString(6, libro.getGeneroliterario());
            statement.setInt(7, libro.getCantidadStock());
            statement.setBoolean(8, libro.isEstadolibro());
            statement.setTimestamp(9, libro.getFecha_creacion());
            statement.executeUpdate();
            mostrarAlerta("Éxito", "Libro registrado correctamente.");
            limpiarCampos();
        } catch (SQLException e) {
            System.out.println("Error al insertar libro: " + e.getMessage());
            mostrarAlerta("Error", "No se pudo registrar el libro.");
        }
    }

    public List<Libro> obtenerTodosLosLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM registrolibro";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
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
                libros.add(libro);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener libros: " + e.getMessage());
        }
        return libros;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        ISBN.clear();
        tituloLibro.clear();
        autorLibro.clear();
        anoPublicacion.clear();
        editorial.clear();
        generoLiterario.clear();
        StockLibro.clear();
    }


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

   

    @FXML
    private void CancelarRegistroLibro(ActionEvent event) {
         limpiarCampos();
    }

    @FXML
    private void regresarMenuAdmin(ActionEvent event) {
        try {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAdmin.fxml"));
        Parent root = loader.load();

        
        Stage stage = (Stage) regresarMenuAdmin.getScene().getWindow();
        
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        mostrarAlerta("Error", "No se pudo cargar la vista del menú principal.");
        e.printStackTrace();
    }
}
        
    
}
    
    
 
