/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author Edgar Chajón
 */
public class Prestamo {
    private int idPrestamo;
    private String id_prestamo;
    private int idUsuario;
    private long ISBN;
    private int cantidadPrestada;
    private LocalDateTime fechaPrestamo;
    private Date fecha_devolucion;
    private LocalDateTime fechaMaxDevolucion;
    private boolean estado;
    private String nombreUsuario;
    
   
    
    
    
    private Map<Long, Integer> librosPrestados;
    
    

    
    
    public Prestamo() {
        
    }

    public Prestamo( String id_prestamo, int idUsuario, long ISBN, int cantidadPrestada, LocalDateTime fechaPrestamo, Date fecha_devolucion, LocalDateTime fechaMaxDevolucion, boolean estado,  Map<Long, Integer> librosPrestados) {
        //this.idPrestamo = idPrestamo;
        this.id_prestamo=id_prestamo;
        this.idUsuario = idUsuario;
        this.ISBN = ISBN;
        this.cantidadPrestada = cantidadPrestada;
        this.fechaPrestamo = fechaPrestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.fechaMaxDevolucion = fechaMaxDevolucion;
        this.estado = estado;
        
        this.librosPrestados = librosPrestados;
        
    }
   
    
    
    public Prestamo(Long ISBN, Integer cantidadPrestada) {
        this.ISBN = ISBN;
        this.cantidadPrestada = cantidadPrestada;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

   

    public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDateTime getFechaMaxDevolucion() {
        return fechaMaxDevolucion;
    }

    public void setFechaMaxDevolucion(LocalDateTime fechaMaxDevolucion) {
        this.fechaMaxDevolucion = fechaMaxDevolucion;
    }

    public Map<Long, Integer> getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(Map<Long, Integer> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public int getCantidadPrestada() {
        return cantidadPrestada;
    }

    public void setCantidadPrestada(int cantidadPrestada) {
        this.cantidadPrestada = cantidadPrestada;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(String id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

   

    
    
    

}