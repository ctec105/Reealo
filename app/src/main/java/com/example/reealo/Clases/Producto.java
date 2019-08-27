package com.example.reealo.Clases;

public class Producto {

    private int idImagen;
    private String nombre;
    private String stock;
    private String precio;
    private String precioOferta;

    public Producto(int idImagen, String nombre, String stock, String precio, String precioOferta) {
        this.idImagen = idImagen;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.precioOferta = precioOferta;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(String precioOferta) {
        this.precioOferta = precioOferta;
    }
}
