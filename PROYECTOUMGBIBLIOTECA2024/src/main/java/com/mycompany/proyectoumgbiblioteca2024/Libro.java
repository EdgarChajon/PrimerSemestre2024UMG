/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoumgbiblioteca2024;

import java.sql.Timestamp;

/**
 *
 * @author Edgar Chajón
 */
public class Libro {
   
    private long ISBN;
    private String tituloLibro;
    private String autorLibro;
    private long anoPublicacion;
    private String editorial;
    private String generoliterario;
    private int cantidadStock;
    private boolean estadolibro;
    private Timestamp fecha_creacion;

    public Libro() {
    }

    public Libro(long ISNB, String tituloLibro, String autorLibro, long anoPublicacion, String editorial, String generoliterario, int cantidadStock, boolean estadolibro, Timestamp fecha_creacion) {
        this.ISBN = ISNB;
        this.tituloLibro = tituloLibro;
        this.autorLibro = autorLibro;
        this.anoPublicacion = anoPublicacion;
        this.editorial = editorial;
        this.generoliterario = generoliterario;
        this.cantidadStock = cantidadStock;
        this.estadolibro = estadolibro;
        this.fecha_creacion = fecha_creacion;
    }
    
    

    
    

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(String autorLibro) {
        this.autorLibro = autorLibro;
    }

    public long getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(long anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    
    

    public String getGeneroliterario() {
        return generoliterario;
    }

    public void setGeneroliterario(String generoliterario) {
        this.generoliterario = generoliterario;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public boolean isEstadolibro() {
        return estadolibro;
    }

    public void setEstadolibro(boolean estadolibro) {
        this.estadolibro = estadolibro;
    }

    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Timestamp fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    
    
    
    
    
    
    
}
