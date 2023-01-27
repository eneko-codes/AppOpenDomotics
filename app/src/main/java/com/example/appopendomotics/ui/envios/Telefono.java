package com.example.appopendomotics.ui.envios;

public class Telefono {
        public String telefonoDele;
        public String emailDele;
        public Telefono(){
        }
        public Telefono(String telefonoDele, String emailDele){
            this.telefonoDele = telefonoDele;
            this.emailDele = emailDele;
        }
        public void setEmailDele(String emailDele) {
            this.emailDele = emailDele;
        }
        public void setTelefonoDele(String telefonoDele) {
            this.telefonoDele = telefonoDele;
        }
        public String getEmailDele() {
            return emailDele;
        }
        public String getTelefonoDele() {
            return telefonoDele;
        }
    }
