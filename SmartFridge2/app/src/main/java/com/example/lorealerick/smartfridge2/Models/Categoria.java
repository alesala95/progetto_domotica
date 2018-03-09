package com.example.lorealerick.smartfridge2.Models;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class Categoria {

    String nome;
    ArrayList <Ricetta> ricette;

    public Categoria(String nome, ArrayList <Ricetta> ricette) {

        this.ricette = ricette;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Ricetta> getRicette() {
        return ricette;
    }

    public void setRicette(ArrayList<Ricetta> ricette) {
        this.ricette = ricette;
    }
}
