package com.example.lorealerick.smartfridge2.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LoreAleRick on 10/03/2018.
 */

public class Services {

    private static Services services;
    private Retrofit retrofit;
    private Retrofit.Builder builder;
    private static final String url = "smartfridgeifts.altervista.org";

    private boolean scaricatoAnteprimaRicette;
    private boolean scaricatoAlimenti;

    public static Services getInstance() {

        if(services == null)
            services = new Services();

        return services;
    }

    private Services() {

        builder = new Retrofit.Builder()
                .baseUrl("http://"+url)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.build();

        scaricatoAnteprimaRicette = false;
        scaricatoAlimenti = false;
    }

    public Retrofit getRetrofit (){

        return retrofit;
    }

    public boolean isScaricatoAlimenti() {
        return scaricatoAlimenti;
    }

    public void setScaricatoAlimenti(boolean scaricatoAlimenti) {
        this.scaricatoAlimenti = scaricatoAlimenti;
    }

    public boolean isScaricatoAnteprimaRicette() {
        return scaricatoAnteprimaRicette;
    }

    public void setScaricatoAnteprimaRicette(boolean scaricatoAnteprimaRicette) {
        this.scaricatoAnteprimaRicette = scaricatoAnteprimaRicette;
    }
}
