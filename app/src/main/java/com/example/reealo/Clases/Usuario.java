package com.example.reealo.Clases;

public class Usuario {

    private int codigo;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;

    public Usuario(int codigo, String nombre, String apellido, String correo, String contraseña) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public Usuario(String nombre, String apellido, String correo, String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigo=" + codigo + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", password=" + contraseña + '}';
    }

}
