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
import java.time.LocalDateTime;
import java.util.HashMap;
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
public class AceptarDevolucionLibroController implements Initializable {

    //private TextField IdPrestamo;
    @FXML
    private TableView<Prestamo> tablaPrestamos;
    //private TableColumn<Prestamo, Integer> IdPrestamo1;
    @FXML
    private TableColumn<Prestamo, Integer> IdUsuario;
    @FXML
    private TableColumn<Prestamo, Long> ISBNLibro;
    @FXML
    private TableColumn<Prestamo, Integer> CantidadPrestada;
    @FXML
    private TableColumn<Prestamo, LocalDate> FechaPrestamo;
    @FXML
    private TableColumn<Prestamo, LocalDate> FechaDevolucion;
    @FXML
    private TableColumn<Prestamo, LocalDate> FechaVencimiento;
    @FXML
    private TableColumn<Prestamo, Boolean> EstadodePrestamo;
    @FXML
    private Button RegresarMenuAdmin;
    
    private Connection conexionBD;
    
    private ObservableList<Prestamo> listaPrestamos;
    @FXML
    private TextField Id_prestamo;
    @FXML
    private Button AceptarDevolucion;
    @FXML
    private Button BuscarId_prestamo;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         CConexion cConexion = new CConexion();
        conexionBD = cConexion.establecer();
        
       IdUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        ISBNLibro.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        CantidadPrestada.setCellValueFactory(new PropertyValueFactory<>("cantidadPrestada"));
        FechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        FechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fecha_devolucion"));
        FechaVencimiento.setCellValueFactory(new PropertyValueFactory<>("fechaMaxDevolucion"));
        EstadodePrestamo.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        
        listaPrestamos = FXCollections.observableArrayList();
        tablaPrestamos.setItems(listaPrestamos);
        // TODO
    } 
    
    @FXML
    private void BuscarId_prestamo(ActionEvent event) {
        try {
            String idPrestamo = Id_prestamo.getText(); // Obtener el id_prestamo desde el TextField
            String selectSQL = "SELECT * FROM prestamo WHERE id_prestamo = ?";
            PreparedStatement preparedStatement = conexionBD.prepareStatement(selectSQL);
            preparedStatement.setString(1, idPrestamo);
            ResultSet resultSet = preparedStatement.executeQuery();

            listaPrestamos.clear();
            while (resultSet.next()) {
                String id_prestamo = resultSet.getString("id_prestamo");
                int idUsuario = resultSet.getInt("Usuario_id");
                long isbn = resultSet.getLong("isbn");
                int cantidadPrestada = resultSet.getInt("cantidad_prestada");
                LocalDateTime fechaPrestamo = resultSet.getTimestamp("fecha_prestamo").toLocalDateTime();
                Date fechaDevolucion = resultSet.getDate("fecha_devolucion");
                LocalDateTime fechaMaxDevolucion = resultSet.getTimestamp("fecha_vencimiento").toLocalDateTime();
                boolean estado = resultSet.getBoolean("estado");

                Prestamo prestamo = new Prestamo(id_prestamo, idUsuario, isbn, cantidadPrestada, fechaPrestamo, fechaDevolucion, fechaMaxDevolucion, estado, null);

                listaPrestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        
        
    }

    


        
        
        
    

    @FXML
    private void RegresarMenuAdmin(ActionEvent event) {
        
        try{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/proyectoumgbiblioteca2024/MenuAdmin.fxml"));
          Parent root=loader.load();
          
          Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      Stage currentStage = (Stage) RegresarMenuAdmin.getScene().getWindow();
      currentStage.close();
      }catch (IOException ex) {
        ex.printStackTrace();
        System.out.println("Error al cargar la ventana RegistrarLibro");
    }
        
    
}


    @FXML
    private void AceptarDevolucion(ActionEvent event) {
        
        try {
        String idPrestamo = Id_prestamo.getText(); // Obtener el id_prestamo desde el TextField

        // Actualizar la fecha de devolución y el estado del préstamo en la tabla 'prestamo'
        String updatePrestamoSQL = "UPDATE prestamo SET fecha_devolucion = ?, estado = TRUE WHERE id_prestamo = ?";
        PreparedStatement preparedStatement = conexionBD.prepareStatement(updatePrestamoSQL);
        preparedStatement.setDate(1, Date.valueOf(LocalDate.now())); // Obtener la fecha actual
        preparedStatement.setString(2, idPrestamo);
        preparedStatement.executeUpdate();

        // Obtener todos los libros prestados en este préstamo para actualizar el stock
        String selectISBNSQL = "SELECT isbn, cantidad_prestada FROM prestamo WHERE id_prestamo = ?";
        preparedStatement = conexionBD.prepareStatement(selectISBNSQL);
        preparedStatement.setString(1, idPrestamo);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            long isbn = resultSet.getLong("isbn");
            int cantidadPrestada = resultSet.getInt("cantidad_prestada");
            
            // Actualizar el stock en la tabla 'registrolibro'
            String updateStockSQL = "UPDATE registrolibro SET cantidadstock = cantidadstock + ? WHERE isbn = ?";
            PreparedStatement updateStatement = conexionBD.prepareStatement(updateStockSQL);
            updateStatement.setInt(1, cantidadPrestada);
            updateStatement.setLong(2, isbn);
            updateStatement.executeUpdate();
            updateStatement.close();
        }

        // Actualizar la tabla después de la devolución
        BuscarId_prestamo(event);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    
    

    
     
    

}
