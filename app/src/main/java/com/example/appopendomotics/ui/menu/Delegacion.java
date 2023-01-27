package com.example.appopendomotics.ui.menu;

public class Delegacion {
    public String delegacionDele;
    public String direccionDele;
    public String telefonoDele;
    public String emailDele;
    public Delegacion(){
    }
    public Delegacion(String delegacionDele, String direccionDele, String telefonoDele, String emailDele){
        this.delegacionDele = delegacionDele;
        this.direccionDele = direccionDele;
        this.telefonoDele = telefonoDele;
        this.emailDele = emailDele;
    }

    public void setDireccionDele(String direccionDele) {
        this.direccionDele = direccionDele;
    }

    public void setDelegacionDele(String delegacionDele) {
        this.delegacionDele = delegacionDele;
    }

    public void setEmailDele(String emailDele) {
        this.emailDele = emailDele;
    }
    public void setTelefonoDele(String telefonoDele) {
        this.telefonoDele = telefonoDele;
    }

    public String getDelegacionDele() {
        return delegacionDele;
    }

    public String getDireccionDele() {
        return direccionDele;
    }

    public String getEmailDele() {
        return emailDele;
    }

    public String getTelefonoDele() {
        return telefonoDele;
    }
}
