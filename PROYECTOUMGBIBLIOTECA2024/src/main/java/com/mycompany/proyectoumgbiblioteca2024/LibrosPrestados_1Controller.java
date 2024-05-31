/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class LibrosPrestados_1Controller implements Initializable {

    @FXML
    private TableView<LibroPrestado> LibrosPrestados;
    @FXML
    private TableColumn<LibroPrestado, Long> COLISBN;
    @FXML
    private TableColumn<LibroPrestado, Integer> UnidadesPrestadas;
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
    private Button RegresarMenuUsuario;

    private ObservableList<LibroPrestado> listaLibrosPrestados;
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando controlador...");
        configurarTabla();
    }

    private void configurarTabla() {
        COLISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        UnidadesPrestadas.setCellValueFactory(new PropertyValueFactory<>("cantidadPrestada"));

        // Inicialización de la lista
        listaLibrosPrestados = FXCollections.observableArrayList();
        LibrosPrestados.setItems(listaLibrosPrestados);
        System.out.println("Tabla configurada.");
    }

    public void setDatosPrestamo(int idPrestamo, int idUsuario, String nombreUsuario, LocalDateTime fechaPrestamo, LocalDateTime fechaMaxDevolucion, Map<Long, Integer> librosPrestados) {
        IdPrestamo.setText(String.valueOf(idPrestamo));
        IdUsuario.setText(String.valueOf(idUsuario));
        nombre.setText(nombreUsuario);
        FechaPrestamo.setText(fechaPrestamo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        fechaMaximaDevolucion.setText(fechaMaxDevolucion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // Limpiar y añadir los libros prestados
        listaLibrosPrestados.clear();
        for (Map.Entry<Long, Integer> entry : librosPrestados.entrySet()) {
            listaLibrosPrestados.add(new LibroPrestado(entry.getKey(), entry.getValue()));
            System.out.println("Añadiendo libro: ISBN=" + entry.getKey() + ", cantidad=" + entry.getValue());
        }
       LibrosPrestados.refresh();
        System.out.println("Datos de préstamo establecidos.");
    }


    @FXML
    private void RegresarMenuUsuario(ActionEvent event) {
        
    }
    
    }
    
        // Lógica para regresar al menú de usuario
    

  