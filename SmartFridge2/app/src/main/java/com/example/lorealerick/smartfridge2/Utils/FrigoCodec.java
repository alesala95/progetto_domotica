package com.example.lorealerick.smartfridge2.Utils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LoreAleRick on 24/03/2018.
 */

public class FrigoCodec {

    @SerializedName("codiceFrigo")
    String codiceFrigo;

    public FrigoCodec (){

    }

    public String getCodiceFrigo (){

        return codiceFrigo;
    }

    public void setCodiceFrigo (String codiceFrigo){

        this.codiceFrigo = codiceFrigo;
    }
}
