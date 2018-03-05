package com.example.itsadmin.smartfridge_fragment.SmartFridgeAPI;

import com.example.itsadmin.smartfridge_fragment.Models.Alimento;
import com.example.itsadmin.smartfridge_fragment.Models.Ricetta;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by itsadmin on 05/03/2018.
 */

public interface RicetteAPI {

    @GET("ricette.php")
    Call<ArrayList<Ricetta>> getAllRicette();

    @GET("/ricette.php")
    Call<ArrayList<Ricetta>> getRicetteCat (@QueryMap Map<String,Object> values);

}
