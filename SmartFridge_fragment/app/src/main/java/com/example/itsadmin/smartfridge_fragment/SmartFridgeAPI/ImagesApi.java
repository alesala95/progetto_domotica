package com.example.itsadmin.smartfridge_fragment.SmartFridgeAPI;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by LoreAleRick on 07/03/2018.
 */

public interface ImagesApi {

    @GET("img_alimenti/{img_url}")
    Call <ResponseBody> getImage (@Path(value = "img_url", encoded = true) String imgUrl);

}
