/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Edgar Chajón
 */
public class HistorialAdminController implements Initializable {

    @FXML
    private TextField IdUsuario;
    @FXML
    private TableView<Prestamo> tablaPrestamos;
    @FXML
    private TableColumn<Prestamo, String> IdPrestamo;
    @FXML
    private TableColumn<Prestamo, Long> ISBNLibro;
    @FXML
    private TableColumn<Prestamo, Integer> CantidadPrestada;
    @FXML
    private TableColumn<Prestamo, LocalDate> FechaPrestamo;
    @FXML
    private TableColumn<Prestamo, LocalDate > FechaDevolucion;
    @FXML
    private TableColumn<Prestamo, LocalDate> FechaVencimiento;
    @FXML
    private TableColumn<Prestamo,Boolean> EstadodePrestamo;
    @FXML
    private Button RegresarMenuAdmin;
    
    @FXML
    private Button BuscarHistorialdePrestamo;
    
    private Connection conexionBD;
   
    private ObservableList<Prestamo> listaPrestamos;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CConexion cConexion = new CConexion();
        conexionBD = cConexion.establecer();
        
        
         
        
       IdPrestamo.setCellValueFactory(new PropertyValueFactory<>("id_prestamo"));
        ISBNLibro.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        CantidadPrestada.setCellValueFactory(new PropertyValueFactory<>("cantidadPrestada"));
        FechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        FechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fecha_devolucion"));
        FechaVencimiento.setCellValueFactory(new PropertyValueFactory<>("fechaMaxDevolucion"));
        EstadodePrestamo.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        
        EstadodePrestamo.setCellFactory(new Callback<TableColumn<Prestamo, Boolean>, TableCell<Prestamo, Boolean>>() {
        public TableCell<Prestamo, Boolean> call(TableColumn<Prestamo, Boolean> column) {
                return new TableCell<Prestamo, Boolean>() {
                    @Override
                    protected void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item ? "Inactivo" : "Activo");
                        }
                    }
                };
            }
        });
                        
                          
                    
                
            
  listaPrestamos = FXCollections.observableArrayList();
   tablaPrestamos.setItems(listaPrestamos);
        // TODO
    }    

    @FXML
    private void BuscarHistorialdePrestamo(ActionEvent event) {
        try {
            int idUsuario = Integer.parseInt(IdUsuario.getText());
            String sql = "SELECT * FROM prestamo WHERE Usuario_id = ?";
            PreparedStatement statement = conexionBD.prepareStatement(sql);
            statement.setInt(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();

            listaPrestamos.clear();

            while (resultSet.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setId_prestamo(resultSet.getString("id_prestamo"));
                prestamo.setIdUsuario(resultSet.getInt("Usuario_id"));
                prestamo.setNombreUsuario(""); // prueba de nombre
         
                prestamo.setISBN(resultSet.getLong("isbn"));
                
                prestamo.setCantidadPrestada(resultSet.getInt("cantidad_prestada"));
                prestamo.setFechaPrestamo(resultSet.getTimestamp("fecha_prestamo").toLocalDateTime());
               // prestamo.setfecha_devolucion(resultSet.getDate("fecha_devolucion") != null ? resultSet.getDate("fecha_devolucion").toLocalDate() : null);
             
                 Date fechaDevolucion = resultSet.getDate("fecha_devolucion");
                if (fechaDevolucion != null) {
                    prestamo.setFecha_devolucion(fechaDevolucion);
                } else {
                    prestamo.setFecha_devolucion(null);
                }
                
                
              
                
                
             
              prestamo.setFechaMaxDevolucion(resultSet.getTimestamp("fecha_vencimiento").toLocalDateTime());
                prestamo.setEstado(resultSet.getBoolean("estado"));
                //tablaPrestamos.getItems().add(prestamo);
                listaPrestamos.add(prestamo);
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void RegresarMenuAdmin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAdmin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) RegresarMenuAdmin.getScene().getWindow();
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
