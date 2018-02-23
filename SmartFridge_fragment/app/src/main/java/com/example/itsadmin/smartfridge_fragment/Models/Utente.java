package com.example.itsadmin.smartfridge_fragment.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by itsadmin on 23/02/2018.
 */

public class Utente {

    @SerializedName("Email")
    String email;
    @SerializedName("Nome")
    String nome;
    @SerializedName("Cognome")
    String cognome;
    @SerializedName("Password")
    String password;

    public Utente(String email,String nome,String cognome,String password) {

        this.email=email;
        this.nome=nome;
        this.cognome=cognome;
        this.password=password;

    }

    public Utente(Utente utente) {

        this.email=utente.getEmail();
        this.nome=utente.getNome();
        this.cognome=utente.getCognome();
        this.password=utente.getPassword();

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
