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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
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
public class PrestamoLibroController implements Initializable {

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
    private TableColumn<Libro, String> COLGenero;
    @FXML
    private TableColumn<Libro, Integer> COLStock;
    @FXML
    private Button buscarLibroporISBN;
    @FXML
    private TextField ISBN;
    @FXML
    private TextField tituloLibro;
    @FXML
    private Button buscarLibroporTitulo;
    @FXML
    private TextField autorLibro;
    @FXML
    private TextField anoPublicacion;
    @FXML
    private TextField editorial;
    @FXML
    private TextField generoLiterario;
    @FXML
    private TextField cantidadenStock;
    @FXML
    private TextField cantidadparaPrestamo;
    @FXML
    private Button ordenarTablaporTitulo;
    @FXML
    private Button ordenarTablaporGenero;
    @FXML
    private Button anadiraCarrito;
    @FXML
    private Button aceptarPrestamo;
    @FXML
    private Button CancelarPrestamo;
    @FXML
    private Button salirDeMenu;
    
    
    private Connection conexionBD;
    
    private ObservableList<Libro> listaLibros;
    
    private List<Libro> carritoLibros = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        CConexion cConexion = new CConexion();
        conexionBD = cConexion.establecer();
        configurarTabla();
        cargarDatosEnTabla();
        
        tvbooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    
    private void configurarTabla() {
        COLISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        COLTItulo.setCellValueFactory(new PropertyValueFactory<>("tituloLibro"));
        COLAutor.setCellValueFactory(new PropertyValueFactory<>("autorLibro"));
        ColAno.setCellValueFactory(new PropertyValueFactory<>("anoPublicacion"));
        COLGenero.setCellValueFactory(new PropertyValueFactory<>("generoliterario"));
        COLStock.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));
    }
    
    private void cargarDatosEnTabla() {
        listaLibros = FXCollections.observableArrayList(obtenerLibrosDisponibles());
        tvbooks.setItems(listaLibros);
    }
    
    public List<Libro> obtenerLibrosDisponibles() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM registrolibro WHERE cantidadstock > 0";
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
            System.err.println("Error al obtener libros: " + e.getMessage());
        }
        return libros;
    }

    @FXML
    private void buscarLibroporISBN(ActionEvent event) {
        String isbnText = ISBN.getText();
        if (isbnText.isEmpty()) {
            cargarDatosEnTabla();
            return;
        }
        
        try {
            long isbn = Long.parseLong(isbnText);
            List<Libro> libros = new ArrayList<>();
            String sql = "SELECT * FROM registrolibro WHERE isbn = ?";
            try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
                statement.setLong(1, isbn);
                try (ResultSet resultSet = statement.executeQuery()) {
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
                }
            }
            listaLibros.setAll(libros);
        } catch (NumberFormatException | SQLException e) {
            System.err.println("Error al buscar por ISBN: " + e.getMessage());
        }
    }

    @FXML
    private void buscarLibroporTitulo(ActionEvent event) {
        String titulo = tituloLibro.getText();
        if (titulo.isEmpty()) {
            cargarDatosEnTabla();
            return;
        }
        
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM registrolibro WHERE titulolibro ILIKE ?";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            statement.setString(1, "%" + titulo + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por título: " + e.getMessage());
        }
        listaLibros.setAll(libros);
    }

    @FXML
    private void ordenarTablaporTitulo(ActionEvent event) {
        listaLibros.sort((libro1, libro2) -> libro1.getTituloLibro().compareToIgnoreCase(libro2.getTituloLibro()));
        tvbooks.refresh();
    }

    @FXML
    private void ordenarTablaporGenero(ActionEvent event) {
        listaLibros.sort((libro1, libro2) -> libro1.getGeneroliterario().compareToIgnoreCase(libro2.getGeneroliterario()));
        tvbooks.refresh();
    }

    @FXML
    private void anadiraCarrito(ActionEvent event) {
        List<Libro> librosSeleccionados = tvbooks.getSelectionModel().getSelectedItems();
        int cantidadPrestamo = Integer.parseInt(cantidadparaPrestamo.getText());

        for (Libro libro : librosSeleccionados) {
            if (libro.getCantidadStock() >= cantidadPrestamo) {
                libro.setCantidadStock(libro.getCantidadStock() - cantidadPrestamo);
                carritoLibros.add(libro);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Stock insuficiente");
                alert.setHeaderText("No hay suficiente stock para el libro: " + libro.getTituloLibro());
                alert.setContentText("Cantidad en stock: " + libro.getCantidadStock());
                alert.showAndWait();
            }
        }
        tvbooks.refresh();
    }

    @FXML
    private void aceptarPrestamo(ActionEvent event) {
        
        
    }
        
    

    @FXML
    private void CancelarPrestamo(ActionEvent event) {
        
    
    }

    @FXML
    private void salirDeMenu(ActionEvent event) {
    }
    
}
