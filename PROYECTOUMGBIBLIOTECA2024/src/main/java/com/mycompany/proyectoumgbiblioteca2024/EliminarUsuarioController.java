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
public class EliminarUsuarioController implements Initializable {

    @FXML
    private TextField BuscarIdUsuario;
    @FXML
    private TextField nombre;
    @FXML
    private TextField direccion;
    @FXML
    private TextField cui;
    @FXML
    private Button AceptarEliminarUsuario;
    @FXML
    private Button CancelarEliminarUsuario;
    @FXML
    private Button regresarMenuAdmin;
    @FXML
    private TextField correo;
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
    @FXML
    private Button buscarIDUsuario;
    @FXML
    private TextField Telefono;
    
     private ObservableList<Usuario> listaUsuario;
     
      private Connection conexionBD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CConexion cConexion = new CConexion();
        conexionBD = cConexion.establecer();
        configurarTabla();
        cargarDatosEnTabla();
    }

    private void configurarTabla() {
        COLNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        COLID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        COLRol.setCellValueFactory(new PropertyValueFactory<>("rolUsuario"));
        Contraseña.setCellValueFactory(new PropertyValueFactory<>("contra"));
    }

    private void cargarDatosEnTabla() {
        listaUsuario = FXCollections.observableArrayList(obtenerTodosLosUsuarios());
        tvUsuario.setItems(listaUsuario);
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
                usuario.setRolUsuario(resultSet.getString("rolUsuario"));
                usuario.setCorreo(resultSet.getString("correo"));
                usuario.setContra(resultSet.getString("contra"));
                usuario.setEstado(resultSet.getBoolean("estado"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    @FXML
    private void buscarIDUsuario(ActionEvent event) {
        String id = BuscarIdUsuario.getText();
        if (id.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingrese un ID.");
            return;
        }

        try {
            Usuario usuario = obtenerUsuarioPorID(Integer.parseInt(id));
            if (usuario != null) {
                cargarDatosEnCampos(usuario);
            } else {
                mostrarAlerta("No encontrado", "No se encontró ningún usuario con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser un número.");
        }
    }

    private Usuario obtenerUsuarioPorID(int id) {
        String sql = "SELECT * FROM usuariobiblioteca WHERE id = ?";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(resultSet.getInt("id"));
                    usuario.setNombre(resultSet.getString("nombre"));
                    usuario.setDireccion(resultSet.getString("direccion"));
                    usuario.setTelefono(resultSet.getLong("telefono"));
                    usuario.setCui(resultSet.getLong("cui"));
                    usuario.setRolUsuario(resultSet.getString("rolUsuario"));
                    usuario.setCorreo(resultSet.getString("correo"));
                    usuario.setContra(resultSet.getString("contra"));
                    usuario.setEstado(resultSet.getBoolean("estado"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuario por ID: " + e.getMessage());
        }
        return null;
    }

    private void cargarDatosEnCampos(Usuario usuario) {
        nombre.setText(usuario.getNombre());
        direccion.setText(usuario.getDireccion());
        Telefono.setText(String.valueOf(usuario.getTelefono()));
        cui.setText(String.valueOf(usuario.getCui()));
        correo.setText(usuario.getCorreo());
    }

    @FXML
    private void AceptarEliminarUsuario(ActionEvent event) {
        String id = BuscarIdUsuario.getText();
        if (id.isEmpty()) {
            mostrarAlerta("Error", "Por favor, ingrese un ID.");
            return;
        }

        try {
            int idUsuario = Integer.parseInt(id);
            eliminarUsuarioPorID(idUsuario);
            limpiarCampos();
            cargarDatosEnTabla();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser un número entero.");
        }
    }

    private void eliminarUsuarioPorID(int id) {
        String sql = "DELETE FROM usuariobiblioteca WHERE id = ?";
        try (PreparedStatement statement = conexionBD.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                mostrarAlerta("Éxito", "Usuario eliminado correctamente.");
            } else {
                mostrarAlerta("Error", "No se encontró el usuario con ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            mostrarAlerta("Error", "No se pudo eliminar el usuario.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        BuscarIdUsuario.clear();
        nombre.clear();
        direccion.clear();
        Telefono.clear();
        cui.clear();
        correo.clear();
    }

    @FXML
    private void CancelarEliminarUsuario(ActionEvent event) {
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
