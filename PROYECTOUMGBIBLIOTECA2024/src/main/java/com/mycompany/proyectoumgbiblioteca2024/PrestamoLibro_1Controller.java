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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
public class PrestamoLibro_1Controller implements Initializable {

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
    
    private SesionUsuario sesion;
    
    private Map<Long, Integer> carritoLibros = new HashMap<>();
    
    
    private Connection conexionBD;
    
    private ObservableList<Libro> listaLibros;
    
    //private List<Libro> carritoLibros = new ArrayList<>();
 
    private Map<Long, Integer> cantidadPorISBN = new HashMap<>();
    @FXML
    private TextField IdUsuario;
    @FXML
    private TextField nombre;
    
    @FXML
    private Button VerLibrosprestados;
    
  
    public void setSesion(SesionUsuario sesion) {
        this.sesion = sesion;
    }
    

   
    private void cargarDatosUsuario() {
       
        SesionUsuario sesion = SesionUsuario.getInstancia();
        if (sesion != null) {
        IdUsuario.setText(String.valueOf(sesion.getId()));
        nombre.setText(sesion.getNombreUsuario());
    }
    }
    
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        CConexion cConexion = new CConexion();
        conexionBD = cConexion.establecer();
        configurarTabla();
        cargarDatosEnTabla();
        cargarDatosUsuario();
        setSesion(SesionUsuario.getInstancia());
        
        //tvbooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
      cantidadenStock.setText(String.valueOf(libro.getCantidadStock()));
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
        
        //prueba
    String isbnTexto = ISBN.getText();
    if (isbnTexto.isEmpty()) {
        mostrarAlerta("Error", "Por favor, ingrese un ISBN.");
        return;
    }
    String cantidadTexto = cantidadparaPrestamo.getText();
    if (cantidadTexto == null || cantidadTexto.isEmpty()) {
        mostrarAlerta("Error", "Por favor, ingrese una cantidad para préstamo.");
        return;
    }

    int cantidadDeseada;
    try {
        cantidadDeseada = Integer.parseInt(cantidadTexto);
    } catch (NumberFormatException e) {
        mostrarAlerta("Error", "La cantidad debe ser un número.");
        return;
    }
    long isbn;
    try {
        isbn = Long.parseLong(isbnTexto);
    } catch (NumberFormatException e) {
        mostrarAlerta("Error", "El ISBN debe ser un número.");
        return;
    }
    Libro libroSeleccionado = buscarLibroPorISBN(isbn);
    if (libroSeleccionado == null) {
        mostrarAlerta("Error", "No se encontró ningún libro con ese ISBN.");
        return;
    }

    // Validar si hay suficiente stock disponible
    if (libroSeleccionado.getCantidadStock() < cantidadDeseada) {
        mostrarAlerta("Error", "No hay suficiente stock disponible para este libro.");
        return;
    }

    // Agregar el libro al carrito
    if (carritoLibros.containsKey(isbn)) {
        // Si el libro ya está en el carrito, sumamos la nueva cantidad a la existente
        cantidadDeseada += carritoLibros.get(isbn);
    }
    carritoLibros.put(isbn, cantidadDeseada);

    // Mostrar mensaje de éxito
    mostrarAlerta("Éxito", "Libro agregado al carrito con éxito.");

    // Actualizar la cantidad de stock en la base de datos
    actualizarStock(libroSeleccionado, cantidadDeseada);
}
    
    
    //para actualizar el stock
    private void actualizarStock(Libro libro, int cantidad) {
    try {
        String sql = "UPDATE registrolibro SET cantidadstock = cantidadstock - ? WHERE isbn = ?";
        PreparedStatement statement = conexionBD.prepareStatement(sql);
        statement.setInt(1, cantidad);
        statement.setLong(2, libro.getISBN());
        statement.executeUpdate();
        statement.close();
    } catch (SQLException e) {
        System.out.println("Error al actualizar el stock: " + e.getMessage());
    }
}

    @FXML
    private void aceptarPrestamo(ActionEvent event) {
        
        if (sesion == null) {
        mostrarAlerta("Error", "La sesión del usuario no está inicializada.");
        return;
    }

    if (carritoLibros.isEmpty()) {
        mostrarAlerta("Error", "No hay libros en el carrito.");
        return;
    }

    try {
        int idPrestamo = guardarPrestamo();
        actualizarEstadoLibrosPrestados();
        mostrarPrestamoFinal(idPrestamo);
        carritoLibros.clear();
        mostrarAlerta("Éxito", "El préstamo se realizó con éxito.");
    } catch (SQLException | IOException e) {
        System.out.println("Error al aceptar el préstamo: " + e.getMessage());
        mostrarAlerta("Error", "No se pudo realizar el préstamo.");
    }
    }
        //prueba de registro
        
    
   
     private void mostrarPrestamoFinal(int idPrestamo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Prestamofinal_1.fxml"));
        Parent root = loader.load();

        PrestamoFinal_1Controller controller = loader.getController();
        controller.setDatosPrestamo(idPrestamo, sesion.getId(), sesion.getNombreUsuario(), LocalDateTime.now(), (List<Libro>) carritoLibros);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Detalles del Préstamo");
        stage.show();
    
}
    
    private int generarIdPrestamo() {
    // Implementar lógica para generar un nuevo ID de préstamo.
    return (int) (Math.random() * 10000); 
}

    
    private int guardarPrestamo() throws SQLException {
    LocalDateTime fechaPrestamo = LocalDateTime.now();
    int idPrestamo = -1;

    for (Map.Entry<Long, Integer> entry : carritoLibros.entrySet()) {
        long isbn = entry.getKey();
        int cantidadPrestada = entry.getValue();
        Libro libro = buscarLibroPorISBN(isbn);

        try {
            LocalDateTime fechaVencimiento = fechaPrestamo.plusDays(14);
           
            String sql = "INSERT INTO Prestamo (Usuario_id, isbn, cantidad_prestada, fecha_prestamo, fecha_vencimiento) VALUES (?, ?, ?, ?, ?) RETURNING id";
            PreparedStatement statement = conexionBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sesion.getId());
            
            statement.setLong(2, isbn);
            statement.setInt(3, cantidadPrestada);
            statement.setTimestamp(4, Timestamp.valueOf(fechaPrestamo));
            //statement.setTimestamp(5, Timestamp.valueOf(fecha_devolucion))
            statement.setTimestamp(5, Timestamp.valueOf(fechaVencimiento));

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                idPrestamo = rs.getInt(1);
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar el préstamo: " + e.getMessage());
            throw e;
        }
    }

    return idPrestamo;
}
    
    
    private void actualizarEstadoLibrosPrestados() throws SQLException {
    for (Map.Entry<Long, Integer> entry : carritoLibros.entrySet()) {
        long isbn = entry.getKey();
        String sql = "UPDATE registrolibro SET estadolibro = ? WHERE isbn = ?";
        PreparedStatement statement = conexionBD.prepareStatement(sql);
        statement.setBoolean(1, true);
        statement.setLong(2, isbn);
        statement.executeUpdate();
        statement.close();
    }
}
    //private int generarIdPrestamo() {
        
  // return id     
//}
        
    

    @FXML
    private void CancelarPrestamo(ActionEvent event) {
      carritoLibros.clear();
      //cargarDatosEnTabla();  
    
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


    @FXML
    private void VerLibrosprestados(ActionEvent event) {
    }
    
}
