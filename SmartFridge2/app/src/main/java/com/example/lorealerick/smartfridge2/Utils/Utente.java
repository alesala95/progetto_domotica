package com.example.lorealerick.smartfridge2.Utils;

/**
 * Created by LoreAleRick on 10/03/2018.
 */

public class Utente {

    private static Utente utente;
    private String nomeUtente;
    private String password;
    private String codiceFrigo;

    public static Utente getInstance() {

        if(utente == null)
            utente = new Utente();

        return utente;
    }

    private Utente() {

        codiceFrigo = "sf0001ma";
    }

    public String getCodiceFrigo() {
        return codiceFrigo;
    }

    public void setCodiceFrigo(String codiceFrigo) {
        this.codiceFrigo = codiceFrigo;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
