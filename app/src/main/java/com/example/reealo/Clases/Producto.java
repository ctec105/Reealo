package com.example.reealo.Clases;

public class Producto {

    private String codigo;
    private String descripcion;
    private String detalle;
    private int stock;
    private double precio;
    private double precioOferta;
    private String imagen;
    private int cantidad;

    private double precioTotal;
    private double precioOfertaTotal;
    private double subTotal;
    private double descuento;
    private double envio;
    private double total;


    public Producto(String codigo, String descripcion, String detalle, int stock, double precio, double precioOferta, String imagen) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.stock = stock;
        this.precio = precio;
        this.precioOferta = precioOferta;
        this.imagen = imagen;
    }

    public Producto(String codigo, String descripcion, double precio, double precioOferta, String imagen, int cantidad) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.precioOferta = precioOferta;
        this.imagen = imagen;
        this.cantidad = cantidad;
    }


    public Producto(String codigo, String descripcion, String detalle, int stock, double precio, String imagen) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.stock = stock;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Producto(String codigo, String descripcion, double precio, String imagen, int cantidad) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.cantidad = cantidad;
    }


    //    public Producto(String codigo, String descripcion) {
//        this.codigo = codigo;
//        this.descripcion = descripcion;
//    }
//
//    public Producto(String codigo, String descripcion, String detalle) {
//        this.codigo = codigo;
//        this.descripcion = descripcion;
//        this.detalle = detalle;
//    }

    public Producto(String descripcion, String detalle, double precio, String imagen) {
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Producto(String codigo, String descripcion, int cantidad, double precioTotal, double precioOfertaTotal, double subTotal, double impDescuento, double envio, double total, String imagen) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.precioOfertaTotal = precioOfertaTotal;
        this.subTotal = subTotal;
        this.descuento = impDescuento;
        this.envio = envio;
        this.total = total;
        this.imagen = imagen;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public double getPrecioOfertaTotal() {
        return precioOfertaTotal;
    }

    public void setPrecioOfertaTotal(double precioOfertaTotal) {
        this.precioOfertaTotal = precioOfertaTotal;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getEnvio() {
        return envio;
    }

    public void setEnvio(double envio) {
        this.envio = envio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", detalle='" + detalle + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", imagen='" + imagen + '\'' +
                '}';
    }

}
