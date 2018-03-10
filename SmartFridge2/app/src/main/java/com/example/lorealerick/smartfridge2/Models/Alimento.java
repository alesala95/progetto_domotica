package com.example.lorealerick.smartfridge2.Models;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

/**
 * Created by LoreAleRick on 10/03/2018.
 */

public class Alimento {

    @SerializedName("idAlimento")
    int idAlimento;
    @SerializedName("nome")
    String nome;
    @SerializedName("data_inserimento")
    String data_inserimento;
    @SerializedName("stima_scadenza")
    int stima_scadenza;
    @SerializedName("image")
    byte [] image;

    public Alimento(String nome, String data_inserimento, int stima_scadenza, int idAlimento, byte [] image) {
        this.idAlimento = idAlimento;
        this.nome = nome;
        this.data_inserimento = data_inserimento;
        this.stima_scadenza = stima_scadenza;
        this.image = image;
    }

    public Alimento (){

    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_inserimento() {
        return data_inserimento;
    }

    public void setData_inserimento(String data_inserimento) {
        this.data_inserimento = data_inserimento;
    }

    public int getStima_scadenza() {
        return stima_scadenza;
    }

    public void setStima_scadenza(int stima_scadenza) {
        this.stima_scadenza = stima_scadenza;
    }

    @Override
    public String toString(){

        return "ciao sono una "+nome;
    }
}
