package com.example.a4party.Objetos;

public class Productos {
    private String Descripcion;
    private String Precio;

    public Productos() {

    }

    public Productos(String descripcion, String precio) {
        this.Descripcion = descripcion;
        this.Precio = precio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }
}
