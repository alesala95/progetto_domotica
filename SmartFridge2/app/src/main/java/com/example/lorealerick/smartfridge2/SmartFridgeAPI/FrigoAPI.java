package com.example.lorealerick.smartfridge2.SmartFridgeAPI;

import com.example.lorealerick.smartfridge2.Models.Frigo;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by itsadmin on 13/03/2018.
 */

public interface FrigoAPI {

    @GET("/ottieniCodice")
    Call <String> connettiFrigo ();

    @GET("/smartfridge2/infoFrigo.php")
    Call <Frigo> getInfoFrigo (@QueryMap Map<String,Object> values);

    @GET("/smartfridge2/impostazioniFrigo.php")
    Call <ResponseBody> setFrigo (@QueryMap Map<String,Object> values);
}
