package com.example.appopendomotics.ui.partners;

public class Partner {
    public String nombre;
    public String direccion;
    public String cif;
    public String telefono;
    public String email;
    public String comercial;
    public Partner() {
    }
    public Partner(String nombre, String direccion, String poblacion,String cif, String telefono, String email, String comercial){
        this.nombre = nombre;
        this.direccion = direccion;
        this.cif = cif;
        this.telefono = telefono;
        this.email = email;
        this.comercial = comercial;
    }
    public void setCif(String cif) {
        this.cif = cif;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setComercial(String comercial) {
        this.comercial = comercial;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComercial() {
        return comercial;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCif() {
        return cif;
    }



}
