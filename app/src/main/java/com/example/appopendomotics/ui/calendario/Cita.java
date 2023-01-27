package com.example.appopendomotics.ui.calendario;

public class Cita {
    public String titulo;
    public String fecha;
    public String hora;
    public String asunto;
    public Cita(){

    }
    public Cita(String titulo, String fecha, String hora, String asunto){
        this.titulo = titulo;
        this.fecha = fecha;
        this.hora = hora;
        this.asunto = asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getTitulo() {
        return titulo;
    }
}
