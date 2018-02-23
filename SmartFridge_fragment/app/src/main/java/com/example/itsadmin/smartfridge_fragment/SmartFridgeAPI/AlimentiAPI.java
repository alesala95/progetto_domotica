package com.example.itsadmin.smartfridge_fragment.SmartFridgeAPI;

import com.example.itsadmin.smartfridge_fragment.Models.Alimento;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by itsadmin on 22/02/2018.
 */

public interface AlimentiAPI {

    /*
    @GET("alimenti.php")
    Call<ArrayList<Alimento>> getAllAlimenti ();
    */

    @GET("prova.php")
    Call<ArrayList<Alimento>> getAllAlimenti ();

    @GET("alimenti.php")
    Call<Alimento> getAlimento ();


}
