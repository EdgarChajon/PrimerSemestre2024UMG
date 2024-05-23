/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;
import java.security.SecureRandom;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
//import java.sql.Connetion;






/**
 * FXML Controller class
 *
 * @author Edgar Chajón
 */
public class CrearUsuarioController implements Initializable {

    @FXML
    private Button AceptarCrearUsuario;
    @FXML
    private Button regresarMenuAdmin;
    @FXML
    private Button CancelarCrearUsuario;
    @FXML
    private TableView<Usuario> tvUsuario;
    @FXML
    private TableColumn<Usuario, String> COLNombre;
    @FXML
    private TableColumn<Usuario, Integer> COLID;
    @FXML
    private TableColumn<Usuario, Boolean> ColEstado;
    @FXML
    private TableColumn<Usuario, String> COLRol;
    @FXML
    private TableColumn<Usuario, String> Contraseña;
    //Connetion conectar=null;
    
    private Connection conexionBD;
    @FXML
    private TextField nombre;
    @FXML
    private TextField direccion;
    @FXML
    private TextField telefono;
    @FXML
    private TextField cui;
    @FXML
    private TextField correo;
    @FXML
    private TextField rolUsuario;
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CConexion cConexion = new CConexion();
        conexionBD = cConexion.establecer();
        configurarTabla();
        cargarDatosEnTabla(); 
        
         
        // TODO
    } 
    
    private void configurarTabla() {
        COLNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        COLID.setCellValueFactory(new PropertyValueFactory<>("id"));
     
 
        
        ColEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        COLRol.setCellValueFactory(new PropertyValueFactory<>("rolUsuario"));
        Contraseña.setCellValueFactory(new PropertyValueFactory<>("contra"));
        
    }
    
    private void cargarDatosEnTabla() {
        tvUsuario.setItems(FXCollections.observableArrayList(obtenerTodosLosUsuarios()));
    }
@FXML
    private void AceptarCrearUsuario(ActionEvent event) {
        Usuario usuario = new Usuario();
        
        usuario.setNombre(nombre.getText());
        usuario.setDireccion(direccion.getText());
        usuario.setTelefono(Long.parseLong(telefono.getText()));
        usuario.setCui(Long.parseLong(cui.getText()));
        usuario.setEstado(true); // Por defecto, el estado es verdadero
        usuario.setRolUsuario(rolUsuario.getText());
        usuario.setCorreo(correo.getText());
        usuario.setContra(generarContraseña());
        usuario.setFechaCreacion(Timestamp.valueOf(LocalDateTime.now()));
        insertarUsuario(usuario);
        cargarDatosEnTabla();
    }

    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuariobiblioteca (nombre, direccion, telefono, cui, estado, rolusuario, contra, correo, fecha_creacion) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getDireccion());
            statement.setLong(3, usuario.getTelefono());
            statement.setLong(4, usuario.getCui());
            statement.setBoolean(5, usuario.isEstado());
            statement.setString(6, usuario.getRolUsuario());
            statement.setString(7, usuario.getContra());
            statement.setString(8, usuario.getCorreo());
            statement.setTimestamp(9, usuario.getFechaCreacion());
            statement.executeUpdate();
            mostrarAlerta("Éxito", "Usuario registrado correctamente.");
            limpiarCampos();
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
            mostrarAlerta("Error", "No se pudo registrar el usuario.");
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuariobiblioteca";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setDireccion(resultSet.getString("direccion"));
                usuario.setTelefono(resultSet.getLong("telefono"));
              
                usuario.setCui(resultSet.getLong("cui"));
                usuario.setEstado(resultSet.getBoolean("estado"));
                usuario.setRolUsuario(resultSet.getString("rolusuario"));
                usuario.setContra(resultSet.getString("contra"));
                usuario.setFechaCreacion(resultSet.getTimestamp("fecha_creacion"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        nombre.clear();
        direccion.clear();
        cui.clear();
        telefono.clear();
        rolUsuario.clear();
        correo.clear();
        //contra.clear();
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

    @FXML
    private void CancelarCrearUsuario(ActionEvent event) {
        limpiarCampos();
    }
private static final String CARACTERES_PERMITIDOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-=_+";
//variable global estatica

    public static String generarContraseña() {
        SecureRandom random = new SecureRandom();
        StringBuilder contraseña = new StringBuilder(16);

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(CARACTERES_PERMITIDOS.length());
            contraseña.append(CARACTERES_PERMITIDOS.charAt(index));
        }

        return contraseña.toString();
    }
    
    
}
    