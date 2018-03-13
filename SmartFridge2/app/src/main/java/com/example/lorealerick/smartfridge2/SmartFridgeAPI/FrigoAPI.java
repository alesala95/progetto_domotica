package com.example.lorealerick.smartfridge2.SmartFridgeAPI;

import com.example.lorealerick.smartfridge2.Models.Frigo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by itsadmin on 13/03/2018.
 */

public interface FrigoAPI {

    @GET("/smartfridge2/infoFrigo.php")
    Call <Frigo> getInfoFrigo (@QueryMap Map<String,Object> values);
}
