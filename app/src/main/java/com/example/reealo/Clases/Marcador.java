package com.example.reealo.Clases;

public class Marcador {

    private int id;
    private String latitud;
    private String longitud;
    private String titulo;

    public Marcador(int id, String latitud, String longitud, String titulo) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}