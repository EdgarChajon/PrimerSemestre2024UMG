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
    private String nombreUsuario;
    private String direccion;
    private String telefono;
    private long DPI;
    private String correo;
    private int idUsuario;
    private String estadoUsuario;

    public UsuarioBiblioteca(String nombreUsuario, String direccion, String telefono, long DPI, String correo, int idUsuario, String estadoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.direccion = direccion;
        this.telefono = telefono;
        this.DPI = DPI;
        this.correo = correo;
        this.idUsuario = idUsuario;
        this.estadoUsuario=estadoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
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
