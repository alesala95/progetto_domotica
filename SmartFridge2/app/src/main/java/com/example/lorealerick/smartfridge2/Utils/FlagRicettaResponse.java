package com.example.lorealerick.smartfridge2.Utils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LoreAleRick on 25/03/2018.
 */

public class FlagRicettaResponse {

    @SerializedName("aggiunta")
    int aggiunta;
    @SerializedName("ok")
    int ok;

    public FlagRicettaResponse(int aggiunta, int ok) {
        this.aggiunta = aggiunta;
        this.ok = ok;
    }

    public int getAggiunta() {
        return aggiunta;
    }

    public void setAggiunta(int aggiunta) {
        this.aggiunta = aggiunta;
    }

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }
}
