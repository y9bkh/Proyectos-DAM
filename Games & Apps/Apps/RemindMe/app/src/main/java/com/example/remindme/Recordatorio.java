package com.example.remindme;

public class Recordatorio {
    private String recordatorio;
    private boolean estaBorrado;
    private boolean esFavorito;

    public Recordatorio() {
    }

    public Recordatorio(String recordatorio) {
        this.recordatorio = recordatorio;
    }

    public Recordatorio(String recordatorio, boolean estaBorrado, boolean esFavorito) {
        this.recordatorio = recordatorio;
        this.estaBorrado = estaBorrado;
        this.esFavorito = esFavorito;
    }

    public String getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(String recordatorio) {
        this.recordatorio = recordatorio;
    }

    public boolean isEstaBorrado() {
        return estaBorrado;
    }

    public void setEstaBorrado(boolean estaBorrado) {
        this.estaBorrado = estaBorrado;
    }

    public boolean isEsFavorito() {
        return esFavorito;
    }

    public void setEsFavorito(boolean esFavorito) {
        this.esFavorito = esFavorito;
    }

    @Override
    public String toString() {
        return "Recordatorio{" +
                "recordatorio='" + recordatorio + '\'' +
                ", estaBorrado=" + estaBorrado +
                '}';
    }
}
