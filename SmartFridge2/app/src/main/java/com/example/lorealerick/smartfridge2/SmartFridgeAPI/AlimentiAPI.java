package com.example.lorealerick.smartfridge2.SmartFridgeAPI;

import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.Models.Ricetta;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by LoreAleRick on 10/03/2018.
 */

public interface AlimentiAPI {

    @GET("/smartfridge2/alimenti.php")
    Call<ArrayList<Alimento>> getAlimenti (@QueryMap Map<String,Object> values);
}
