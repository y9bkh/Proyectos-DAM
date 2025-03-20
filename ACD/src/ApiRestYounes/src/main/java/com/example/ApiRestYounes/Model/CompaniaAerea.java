package com.example.ApiRestYounes.Model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "ApiRestYounes")
public class CompaniaAerea {
    private ObjectId id;

    private String nombreAerolinea;
    private String paisOrigen; // De donde es la aerolinea
    private int flotaAviones;

    private List<Vuelo> vuelos;

    public CompaniaAerea() {
    }

    public CompaniaAerea(String nombreAerolinea, String paisOrigen, int flotaAviones, List<Vuelo> vuelos) {
        this.nombreAerolinea = nombreAerolinea;
        this.paisOrigen = paisOrigen;
        this.flotaAviones = flotaAviones;
        this.vuelos = vuelos;
    }

    public CompaniaAerea(ObjectId id, String nombreAerolinea, String paisOrigen, int flotaAviones, List<Vuelo> vuelos) {
        this.id = id;
        this.nombreAerolinea = nombreAerolinea;
        this.paisOrigen = paisOrigen;
        this.flotaAviones = flotaAviones;
        this.vuelos = vuelos;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombreAerolinea() {
        return nombreAerolinea;
    }

    public void setNombreAerolinea(String nombreAerolinea) {
        this.nombreAerolinea = nombreAerolinea;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public int getFlotaAviones() {
        return flotaAviones;
    }

    public void setFlotaAviones(int flotaAviones) {
        this.flotaAviones = flotaAviones;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    @Override
    public String toString() {
        return "CompaniaAerea{" +
                "id=" + id +
                ", nombreAerolinea='" + nombreAerolinea + '\'' +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", flotaAviones=" + flotaAviones +
                ", vuelos=" + vuelos +
                '}';
    }
}
