package com.example.itsadmin.smartfridge_fragment.Main.Items;

/**
 * Created by itsadmin on 12/02/2018.
 */

public class ItemListRicetteConsigliate {

    String text;
    int img;


    public ItemListRicetteConsigliate(){


    }



    public ItemListRicetteConsigliate(String text,int img){

        this.text = text;
        this.img=img;

    }


    public String getTesto(){

        return text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
