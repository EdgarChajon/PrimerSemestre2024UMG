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
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
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
        
       // tvbooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        listaLibros = FXCollections.observableArrayList(obtenerTodosLosLibros());
        tvbooks.setItems(listaLibros);
    }
    //ver todos los libros 
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


    @FXML
    private void buscarLibroporISBN(ActionEvent event) {
        String isbn = ISBN.getText();
        if (isbn.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingrese un ISBN.");
            return;
        }

        try {
            Libro libro = buscarLibroPorISBN(Long.parseLong(isbn));
            if (libro != null) {
                tituloLibro.setText(libro.getTituloLibro());
                autorLibro.setText(libro.getAutorLibro());
                anoPublicacion.setText(String.valueOf(libro.getAnoPublicacion()));
                editorial.setText(libro.getEditorial());
                generoLiterario.setText(libro.getGeneroliterario());
                cantidadenStock.setText(String.valueOf(libro.getCantidadStock()));
                
            } else {
                mostrarAlerta("No encontrado", "No se encontró ningún libro con ese ISBN.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ISBN debe ser un número.");
        }
    }

    private Libro buscarLibroPorISBN(long isbn) {
        for (Libro libro : listaLibros) {
            if (libro.getISBN() == isbn) {
                return libro;
            }
        }
        return null;
    
    }
        
        
        
    
    
    

    @FXML
    private void buscarLibroporTitulo(ActionEvent event) {
        String titulo = tituloLibro.getText();
    if (titulo.isEmpty()) {
        mostrarAlerta("Error", "Por favor, ingrese un título.");
        return;
    }

    Libro libro = buscarLibroPorTitulo(titulo);
    if (libro != null) {
        ISBN.setText(String.valueOf(libro.getISBN()));
        autorLibro.setText(libro.getAutorLibro());
        anoPublicacion.setText(String.valueOf(libro.getAnoPublicacion()));
        editorial.setText(libro.getEditorial());
        generoLiterario.setText(libro.getGeneroliterario());
        COLStock.setText(String.valueOf(libro.getCantidadStock()));
    } else {
        mostrarAlerta("No encontrado", "No se encontró ningún libro con ese título.");
    }
}

    private Libro buscarLibroPorTitulo(String titulo) {
    for (Libro libro : listaLibros) {
        if (libro.getTituloLibro().toLowerCase().contains(titulo.toLowerCase())) {
            return libro;
        }
    }
    return null;
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
    int cantidadPrestamo;

    try {
        cantidadPrestamo = Integer.parseInt(cantidadparaPrestamo.getText());
    } catch (NumberFormatException e) {
        mostrarAlerta("Error", "Cantidad para préstamo inválida", "Por favor, ingrese un número válido.", Alert.AlertType.ERROR);
        return;
    }

    for (Libro libro : librosSeleccionados) {
        if (libro.getCantidadStock() >= cantidadPrestamo) {
            libro.setCantidadStock(libro.getCantidadStock() - cantidadPrestamo);
            carritoLibros.add(libro);
        } else {
            mostrarAlerta("Stock insuficiente", "No hay suficiente stock para el libro: " + libro.getTituloLibro(), "Cantidad en stock: " + libro.getCantidadStock(), Alert.AlertType.WARNING);
        }
    }

    tvbooks.refresh();
}
    

    @FXML
    private void aceptarPrestamo(ActionEvent event) {
        try {
            int prestamoId = generarIdPrestamo(); // Generar el ID del préstamo
            if (prestamoId != -1) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PrestamoFinal.fxml"));
                Parent root = loader.load();

                PrestamoFinalController prestamoFinalController = loader.getController();
                String nombreUsuario = "Nombre del Usuario"; // Recuperar el nombre del usuario actual

                prestamoFinalController.setDatosPrestamo(carritoLibros, prestamoId, nombreUsuario);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                Stage currentStage = (Stage) aceptarPrestamo.getScene().getWindow();
                currentStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se pudo generar el préstamo");
                alert.setContentText("Ocurrió un error al intentar generar el préstamo. Por favor, inténtelo nuevamente.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
    }
    
    private int generarIdPrestamo() {
        String sql = "INSERT INTO Prestamo (Usuario_id, isbn, fecha_prestamo, fecha_devolucion, estado, fecha_vencimiento) VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?, ?)";
        int prestamoId = -1;

        try (PreparedStatement statement = conexionBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (Libro libro : carritoLibros) {
                statement.setInt(1, 1); // Usuario_id: Debe ser reemplazado con el ID del usuario actual
                statement.setLong(2, libro.getISBN());
                statement.setTimestamp(3, null); // fecha_devolucion: null inicialmente
                statement.setBoolean(4, false); // estado: false inicialmente
                statement.setTimestamp(5, Timestamp.valueOf(LocalDate.now().plusDays(14).atStartOfDay())); // fecha_vencimiento: 14 días después de la fecha actual
                statement.addBatch();
            }

            statement.executeBatch();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    prestamoId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al generar el ID del préstamo: " + e.getMessage());
        }

        return prestamoId;
          }
        
    

    @FXML
    private void CancelarPrestamo(ActionEvent event) {
      carritoLibros.clear();
      cargarDatosEnTabla();  
    
    }

    @FXML
    private void salirDeMenu(ActionEvent event) {
    }
    
    private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
    Alert alert = new Alert(tipo);
    alert.setTitle(titulo);
    alert.setHeaderText(encabezado);
    alert.setContentText(contenido);
    alert.showAndWait();
}
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
