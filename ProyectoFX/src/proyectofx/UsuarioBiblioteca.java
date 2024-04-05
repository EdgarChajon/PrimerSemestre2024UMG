/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofx;

/**
 *
 * @author Edgar Chajón
 */
public class UsuarioBiblioteca {
    private String nombre;
    private String direccion;
    private String telefono;
    private long DPI;
    private String correo;
    private String idUsuario;

    public UsuarioBiblioteca(String nombre, String direccion, String telefono, long DPI, String correo, String idUsuario) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.DPI = DPI;
        this.correo = correo;
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long getDPI() {
        return DPI;
    }

    public void setDPI(long DPI) {
        this.DPI = DPI;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public void PrestarLibro(){
        
    }
    public void DevolucionLibro(){
        
    }
    public void CrearCuenta(){
        
    }
    public void BuscarLibro(){
        
    }
    public void Verhistorial(){
        
    }
    
    
    
    
}
