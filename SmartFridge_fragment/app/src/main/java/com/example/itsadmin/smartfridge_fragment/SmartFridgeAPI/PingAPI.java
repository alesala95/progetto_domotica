package com.example.itsadmin.smartfridge_fragment.SmartFridgeAPI;

import com.example.itsadmin.smartfridge_fragment.Models.Alimento;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by itsadmin on 22/02/2018.
 */

public interface PingAPI {

    @GET("alimenti.php")
    Call<ResponseBody> ping ();
}
