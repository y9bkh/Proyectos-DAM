package com.example.reparapablo.models;

import java.io.Serializable;

public class Incidencia implements Serializable {
    private String id;
    private String titulo;
    private String ubicacion;
    private String descripcion;
    private String fecha;
    private String estado;
    private String reportadoPor;
    private String asignadoA;
    private String uid;

    public Incidencia() {}



    public Incidencia(String titulo, String ubicacion, String descripcion, String fecha, String uid) {
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.reportadoPor = reportadoPor;
        this.uid = uid;
    }

    public Incidencia(String titulo, String ubicacion, String descripcion, String fecha, String estado, String reportadoPor, String asignadoA, String uid) {
        this.titulo = titulo;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
        this.reportadoPor = reportadoPor;
        this.asignadoA = asignadoA;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReportadoPor() {
        return reportadoPor;
    }

    public void setReportadoPor(String reportadoPor) {
        this.reportadoPor = reportadoPor;
    }

    public String getAsignadoA() {
        return asignadoA;
    }

    public void setAsignadoA(String asignadoA) {
        this.asignadoA = asignadoA;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
