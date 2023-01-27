package com.example.appopendomotics.ui.pedidos;

public class Pedidos {
    public String nPedidos;
    public String fechaPedido;
    public String fechaPago;
    public String fechaEnvio;
    public int nComercial;
    public String delegacionProvincial;
    public String partner;
    public int codigo;
    public int cantidad;
    public int precio;
    public int imagen;
    public Pedidos(){
    }
    public Pedidos(String nPedidos, String fechaPedido,String fechaPago, String fechaEnvio, int nComercial, String delegacionProvincial,String partner,int codigo, int cantidad, int precio,int imagen){
        this.nPedidos = nPedidos;
        this.fechaPedido = fechaPedido;
        this.fechaPago = fechaPago;
        this.fechaEnvio = fechaEnvio;
        this.nComercial = nComercial;
        this.delegacionProvincial = delegacionProvincial;
        this.partner = partner;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.imagen = imagen;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDelegacionProvincial(String delegacionProvincial) {
        this.delegacionProvincial = delegacionProvincial;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setnComercial(int nComercial) {
        this.nComercial = nComercial;
    }

    public void setnPedidos(String nPedidos) {
        this.nPedidos = nPedidos;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getImagen() {
        return imagen;
    }

    public int getnComercial() {
        return nComercial;
    }

    public String getnPedidos() {
        return nPedidos;
    }

    public int getPrecio() {
        return precio;
    }

    public String getDelegacionProvincial() {
        return delegacionProvincial;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public String getPartner() {
        return partner;
    }
}
