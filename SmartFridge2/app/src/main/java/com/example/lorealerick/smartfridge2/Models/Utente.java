package com.example.lorealerick.smartfridge2.Models;

import com.example.lorealerick.smartfridge2.Utils.UtenteCorrente;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LoreAleRick on 16/03/2018.
 */

public class Utente {

    @SerializedName("nome")
    private String nome;
    @SerializedName("cognome")
    private String cognome;
    @SerializedName("email")
    private String eMail;
    @SerializedName("password")
    private String password;
    @SerializedName("codiceFrigo")
    private String codiceFrigo;

    public Utente(String nome, String cognome, String eMail, String password, String codiceFrigo) {
        this.nome = nome;
        this.cognome = cognome;
        this.eMail = eMail;
        this.password = password;
        this.codiceFrigo = codiceFrigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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

    public String getCodiceFrigo() {
        return codiceFrigo;
    }

    public void setCodiceFrigo(String codiceFrigo) {
        this.codiceFrigo = codiceFrigo;
    }

    @Override
    public String toString (){

        return "ciao, sono "+nome+", "+cognome+", la mia mail è "+eMail;
    }
}
