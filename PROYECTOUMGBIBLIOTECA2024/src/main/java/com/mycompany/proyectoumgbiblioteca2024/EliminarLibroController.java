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
public class EliminarLibroController implements Initializable {

    @FXML
    private TextField ISBN;
    @FXML
    private TextField tituloLibro;
    @FXML
    private TextField autorLibro;
    @FXML
    private TextField anoPublicacion;
    @FXML
    private TextField generoLiterario;
    @FXML
    private TextField editorial;
    @FXML
    private Button EliminarLibro;
    @FXML
    private Button CancelarElimnarLibro;
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
    @FXML
    private Button BuscarLibroEliminar;
    
    
    private Connection conexionBD;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        CConexion cConexion = new CConexion();
        conexionBD = cConexion.establecer();
        configurarTabla();
        cargarDatosEnTabla();
    }

    private void configurarTabla() {
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
    private void BuscarLibroEliminar(ActionEvent event) {
        Long isbn = Long.parseLong(ISBN.getText());
        Libro libro = buscarLibroPorISBN(isbn);
        if (libro != null) {
            llenarCamposConLibro(libro);
        } else {
            mostrarAlerta("Error", "No se encontró el libro con ISBN: " + isbn);
        }
    }

    private Libro buscarLibroPorISBN(Long isbn) {
        String sql = "SELECT * FROM registrolibro WHERE isbn = ?";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            statement.setLong(1, isbn);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
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
                    return libro;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar libro: " + e.getMessage());
        }
        return null;
    }

    private void llenarCamposConLibro(Libro libro) {
        ISBN.setText(String.valueOf(libro.getISBN()));
        tituloLibro.setText(libro.getTituloLibro());
        autorLibro.setText(libro.getAutorLibro());
        anoPublicacion.setText(String.valueOf(libro.getAnoPublicacion()));
        editorial.setText(libro.getEditorial());
        generoLiterario.setText(libro.getGeneroliterario());
    }

    @FXML
    private void EliminarLibro(ActionEvent event) {
        Long isbn = Long.parseLong(ISBN.getText());
        eliminarLibroPorISBN(isbn);
        limpiarCampos();
        cargarDatosEnTabla();
    }

    private void eliminarLibroPorISBN(Long isbn) {
        String sql = "DELETE FROM registrolibro WHERE isbn = ?";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            statement.setLong(1, isbn);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                mostrarAlerta("Éxito", "Libro eliminado correctamente.");
            } else {
                mostrarAlerta("Error", "No se encontró el libro con ISBN: " + isbn);
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar libro: " + e.getMessage());
            mostrarAlerta("Error", "No se pudo eliminar el libro.");
        }
    }

    private void cargarDatosEnTabla() {
        tvbooks.setItems(FXCollections.observableArrayList(obtenerTodosLosLibros()));
    }

    private List<Libro> obtenerTodosLosLibros() {
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
    }

    @FXML
    private void CancelarElimnarLibro(ActionEvent event) {
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