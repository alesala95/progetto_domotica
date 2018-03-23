package com.example.lorealerick.smartfridge2.Utils;

/**
 * Created by LoreAleRick on 10/03/2018.
 */

public class UtenteCorrente {

    private static UtenteCorrente utenteCorrente;
    private String nomeUtente;
    private String cognomeUtente;
    private String eMail;
    private String password;
    private String codiceFrigo;

    public static UtenteCorrente getInstance() {

        if(utenteCorrente == null)
            utenteCorrente = new UtenteCorrente();

        return utenteCorrente;
    }

    private UtenteCorrente() {

        //codiceFrigo = "sf0001ma";
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getCognomeUtente() {
        return cognomeUtente;
    }

    public void setCognomeUtente(String cognomeUtente) {
        this.cognomeUtente = cognomeUtente;
    }
}
