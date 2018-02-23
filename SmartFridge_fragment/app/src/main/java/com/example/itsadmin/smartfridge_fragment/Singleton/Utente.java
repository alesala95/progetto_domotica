package com.example.itsadmin.smartfridge_fragment.Singleton;

/**
 * Created by itsadmin on 22/02/2018.
 */

public class Utente {

    private static Utente utente;
    private String eMail;
    private String password;

    private Utente(){


    }

    public static Utente getInstance(){

        if(utente==null)

            utente = new Utente();

        return utente;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
