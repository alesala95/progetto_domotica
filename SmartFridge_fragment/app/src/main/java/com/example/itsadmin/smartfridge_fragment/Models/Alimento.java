package com.example.itsadmin.smartfridge_fragment.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by itsadmin on 22/02/2018.
 */

public class Alimento {


    @SerializedName("nome")
    String nome;
    @SerializedName("quantita")
    String quantita;
    @SerializedName("scadenza")
    Date scadenza;
    @SerializedName("immagine")
    String immagineUrl;

    public Alimento(String nome, String quantita, Date scadenza, String immagineUrl) {
        this.nome = nome;
        this.quantita = quantita;
        this.scadenza = scadenza;
        this.immagineUrl = immagineUrl;
    }

    public Alimento (Alimento alimento){

        this.nome = alimento.getNome();
        this.quantita = alimento.getQuantita();
        this.scadenza = alimento.getScadenza();
        this.immagineUrl = alimento.getImmagineUrl();
    }

    public String getImmagineUrl() {
        return immagineUrl;
    }

    public void setImmagineUrl(String immagineUrl) {
        this.immagineUrl = immagineUrl;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantita() {
        return quantita;
    }

    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    @Override
    public String toString (){

        return "Ciao, sono una "+nome+" e siamo in "+quantita+" e scadiamo il "+scadenza.toString();
    }


}
