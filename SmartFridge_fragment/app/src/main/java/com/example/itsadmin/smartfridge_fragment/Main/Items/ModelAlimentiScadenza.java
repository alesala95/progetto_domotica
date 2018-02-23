package com.example.itsadmin.smartfridge_fragment.Main.Items;

/**
 * Created by itsadmin on 20/02/2018.
 */

public class ModelAlimentiScadenza {

    String nome;
    int img;

    public ModelAlimentiScadenza(String nome, int img) {
        this.nome = nome;
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
