package com.example.itsadmin.smartfridge_fragment.Main.Items;

/**
 * Created by LoreAleRick on 07/02/2018.
 */

public class ItemListaScadenze {

    String testo;
    int img;

    public ItemListaScadenze(String testo,int img){

        this.testo = testo;
        this.img=img;

    }

    public ItemListaScadenze(){


    }

    public String getTesto(){

        return testo;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
