package com.example.ApiRestYounes.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ApiRestYounes")
public class Vuelo {

    private String origen;
    private String destino;
    private double precioVuelo;

    public Vuelo() {
    }

    public Vuelo(String origen, String destino, double precioVuelo) {
        this.origen = origen;
        this.destino = destino;
        this.precioVuelo = precioVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPrecioVuelo() {
        return precioVuelo;
    }

    public void setPrecioVuelo(double precioVuelo) {
        this.precioVuelo = precioVuelo;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", precioVuelo=" + precioVuelo +
                '}';
    }
}
