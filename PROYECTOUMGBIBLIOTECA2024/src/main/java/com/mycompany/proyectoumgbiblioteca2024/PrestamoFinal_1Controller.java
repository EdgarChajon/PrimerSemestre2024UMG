/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class PrestamoFinal_1Controller implements Initializable {

    @FXML
    private TextField IdPrestamo;
    @FXML
    private TextField IdUsuario;
    @FXML
    private TextField nombre;
    @FXML
    private TextField FechaPrestamo;
    @FXML
    private TextField fechaMaximaDevolucion;
    @FXML
    private TableView<Libro> tvbooksPrestados;
    @FXML
    private TableColumn<Libro, Long> COLISBN;
    @FXML
    private TableColumn<Libro, String> COLTItulo;
    @FXML
    private TableColumn<Libro, String> COLAutor;
    @FXML
    private TableColumn<Libro, Long> ColAno;
    @FXML
    private TableColumn<Libro, String> COLGenero;
    @FXML
    private TableColumn<Libro, Integer> UnidadesPrestadas;
    
    private Map<Long, Integer> carritoLibros = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
    }

    private void configurarTabla() {
        COLISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        COLTItulo.setCellValueFactory(new PropertyValueFactory<>("tituloLibro"));
        COLAutor.setCellValueFactory(new PropertyValueFactory<>("autorLibro"));
        ColAno.setCellValueFactory(new PropertyValueFactory<>("anoPublicacion"));
        COLGenero.setCellValueFactory(new PropertyValueFactory<>("generoliterario"));
        UnidadesPrestadas.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));
    }

    public void setDatosPrestamo(int idPrestamo, int idUsuario, String nombreUsuario, LocalDateTime fechaPrestamo, List<Libro> librosPrestados) {
        IdPrestamo.setText(String.valueOf(idPrestamo));
        IdUsuario.setText(String.valueOf(idUsuario));
        nombre.setText(nombreUsuario);
        FechaPrestamo.setText(fechaPrestamo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        fechaMaximaDevolucion.setText(fechaPrestamo.plusDays(14).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        tvbooksPrestados.setItems(FXCollections.observableArrayList(librosPrestados));
    }


    @FXML
    private void aceptarPrestamo(ActionEvent event) {
    }
    
}
