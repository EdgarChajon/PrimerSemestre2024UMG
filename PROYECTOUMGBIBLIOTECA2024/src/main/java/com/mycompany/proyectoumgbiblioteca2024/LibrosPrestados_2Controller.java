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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class LibrosPrestados_2Controller implements Initializable {

  @FXML
    private TextField fechaMaximaDevolucion;
    @FXML
    private TextField FechaPrestamo;
    @FXML
    private TextField nombre;
    @FXML
    private TextField IdUsuario;
    @FXML
    private TextField IdPrestamo;
    @FXML
    private Button RegresarMenuUsuario;
    private TableView<Prestamo> LibrosPrestados_1;
    private TableColumn<Prestamo, Long> COLISBN;
    private TableColumn<Prestamo, Integer> UnidadesPrestadas;

    private ObservableList<LibroPrestado> librosPrestados;
    
    private ObservableList<LibroPrestado> listaLibrosPrestados;
    
     private ObservableList<Prestamo> listaPrestamos;
    
     //private ObservableList<Prestamo>librosPrestados;

     private Connection conexionBD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CConexion cConexion = new CConexion();
        conexionBD = cConexion.establecer();
        
       
      
        
        // Inicialización si es necesario
    }

    public void setDatosPrestamo(int idPrestamo, int idUsuario, String nombreUsuario, LocalDateTime fechaPrestamo, LocalDateTime fechaMaxDevolucion, Map<Long, Integer> librosPrestados) {
        IdPrestamo.setText(String.valueOf(idPrestamo));
        IdUsuario.setText(String.valueOf(idUsuario));
        nombre.setText(nombreUsuario);
        FechaPrestamo.setText(fechaPrestamo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        fechaMaximaDevolucion.setText(fechaMaxDevolucion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        
        
       
    
        
        
       
    }
    
    public void CargarDatosTabla(){
        
        
       
    }
    

        
    

    @FXML
    private void RegresarMenuUsuario(ActionEvent event) {
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/MenuUsuario_1.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) RegresarMenuUsuario.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
      }
         
    }
    
    }