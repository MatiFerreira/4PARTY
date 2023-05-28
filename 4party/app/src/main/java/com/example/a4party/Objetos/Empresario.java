package com.example.a4party.Objetos;

public class Empresario {
    private String dni;
    private String nombre, apellido;
    private String email;
    private String codigoPostal;
    private String nombreEstablecimiento;
    private String urlimagen = "https://firebasestorage.googleapis.com/v0/b/party-a6e37.appspot.com/o/predeterminado.jpg?alt=media&token=3efd8987-5e17-4e7e-99bf-f07e9671a2f4";

    public Empresario() {

    }

    public Empresario(String dni, String nombre, String apellido, String email, String codigoPostal,
                      String nombreEstablecimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.codigoPostal = codigoPostal;
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public Empresario(String dni, String nombre, String apellido, String email, String codigoPostal,
                      String nombreEstablecimiento, String urlimagen) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.codigoPostal = codigoPostal;
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.urlimagen = urlimagen;
    }

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }
}
