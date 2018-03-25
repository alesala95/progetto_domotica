package com.example.lorealerick.smartfridge2.Utils;

import com.google.gson.annotations.SerializedName;



/**
 * Created by LoreAleRick on 24/03/2018.
 */

public class FrigoCodec {

    @SerializedName("success")
    private int success;
    @SerializedName("codiceFrigo")
    private String codiceFrigo;

    public FrigoCodec (){

    }

    public FrigoCodec(int success, String codiceFrigo) {
        this.success = success;
        this.codiceFrigo = codiceFrigo;
    }

    public String getCodiceFrigo (){

        return codiceFrigo;
    }

    public void setCodiceFrigo (String codiceFrigo){

        this.codiceFrigo = codiceFrigo;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
