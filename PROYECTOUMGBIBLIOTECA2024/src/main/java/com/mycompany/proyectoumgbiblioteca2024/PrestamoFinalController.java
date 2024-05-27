/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Edgar Chajón
 */
public class PrestamoFinalController implements Initializable {

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
    private TableColumn<Libro, String> CantidadAPrestamo;
    @FXML
    private TextField IdPrestamo;
    @FXML
    private TextField nombre;
    @FXML
    private TextField fechainicioPrestamo;
    @FXML
    private TextField fechaFinPrestamo;
    @FXML
    private Button AceptarTerminar;
    
     private List<Libro> librosPrestamo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        // TODO
    }   
    private void configurarTabla() {
        COLISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        COLTItulo.setCellValueFactory(new PropertyValueFactory<>("tituloLibro"));
        COLAutor.setCellValueFactory(new PropertyValueFactory<>("autorLibro"));
        ColAno.setCellValueFactory(new PropertyValueFactory<>("anoPublicacion"));
        COLGenero.setCellValueFactory(new PropertyValueFactory<>("generoliterario"));
        CantidadAPrestamo.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));
         }
    
    public void setDatosPrestamo(List<Libro> libros, int prestamoId, String nombreUsuario) {
        this.librosPrestamo = libros;
        tvbooks.setItems(FXCollections.observableArrayList(librosPrestamo));
        tvbooks.refresh();

        IdPrestamo.setText(String.valueOf(prestamoId));
        nombre.setText(nombreUsuario);
        fechainicioPrestamo.setText(obtenerFechaActual());
        fechaFinPrestamo.setText(obtenerFechaFin());
    }

    private String obtenerFechaActual() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private String obtenerFechaFin() {
        return LocalDate.now().plusDays(14).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    @FXML
    private void AceptarTerminar(ActionEvent event) {
    }

    
    
}
