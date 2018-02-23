package com.example.itsadmin.smartfridge_fragment.Singleton;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by itsadmin on 22/02/2018.
 */

public class RetrofitService {

    private static RetrofitService retrofitInstance;
    private Retrofit.Builder builder;
    private Retrofit retrofit;
    private static final String url = "smartfridgeifts.altervista.org";

    private RetrofitService(){

        builder = new Retrofit.Builder()
                .baseUrl("http://"+url)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.build();
    }

    public static RetrofitService getInstance(){

        if(retrofitInstance==null)
            retrofitInstance = new RetrofitService();

        return retrofitInstance;
    }

    public Retrofit getRetrofit (){

        return retrofit;
    }


}
