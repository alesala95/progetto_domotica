package com.example.itsadmin.smartfridge_fragment.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by itsadmin on 23/02/2018.
 */

public class Ricetta {
    @SerializedName("idRicetta")
    int id;
    @SerializedName("Nome")
    String nome;
    @SerializedName("Difficoltà")
    int difficoltà;
    @SerializedName("Durata")
    String durata;
    @SerializedName("Preferita")
    int preferita;

    public Ricetta(int id,String nome,int difficoltà,String durata, int preferita) {

        this.id=id;
        this.nome=nome;
        this.difficoltà=difficoltà;
        this.durata=durata;
        this.preferita=preferita;
    }

    public Ricetta(Ricetta ricetta) {

        this.id=ricetta.getId();
        this.nome=ricetta.getNome();
        this.difficoltà=ricetta.getDifficoltà();
        this.durata=ricetta.getDurata();
        this.preferita=ricetta.getPreferita();

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

    public int getDifficoltà() {
        return difficoltà;
    }

    public void setDifficoltà(int difficoltà) {
        this.difficoltà = difficoltà;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public int getPreferita() {
        return preferita;
    }

    public void setPreferita(int preferita) {
        this.preferita = preferita;
    }
}
