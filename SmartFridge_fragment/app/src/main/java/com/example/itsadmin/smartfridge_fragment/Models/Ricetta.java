package com.example.itsadmin.smartfridge_fragment.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by itsadmin on 23/02/2018.
 */

public class Ricetta {
    @SerializedName("idRicetta")
    int id;
    @SerializedName("nome")
    String nome;
    @SerializedName("difficolta")
    int difficolta;
    @SerializedName("durata")
    String durata;
    @SerializedName("autore")
    String autore;
    @SerializedName("ingredienti")
    String ingredienti;
    @SerializedName("procedimento")
    String procedimento;
    @SerializedName("urlImage")
    String urlImage;

    public Ricetta(int id, String nome, int difficolta, String durata, String autore, String ingredienti, String procendimento, String urlImage) {
        this.id = id;
        this.nome = nome;
        this.difficolta = difficolta;
        this.durata = durata;
        this.autore = autore;
        this.ingredienti = ingredienti;
        this.procedimento = procendimento;
        this.urlImage = urlImage;
    }


    public Ricetta (Ricetta r){

        this.id = r.getId();
        this.nome = r.getNome();
        this.difficolta = r.getDifficolta();
        this.durata = r.getDurata();
        this.autore = r.getAutore();
        this.ingredienti = r.getIngredienti();
        this.procedimento = r.getProcedimento();
        this.urlImage = r.getUrlImage();
    }

    public String getUrlImage(){

        return urlImage;
    }

    public void setUrlImage (String urlImage){

        this.urlImage = urlImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDifficolta() {
        return difficolta;
    }

    public void setDifficolta(int difficolta) {
        this.difficolta = difficolta;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    @Override
    public String toString (){

        return "Ciao sono "+nome;
    }
}


