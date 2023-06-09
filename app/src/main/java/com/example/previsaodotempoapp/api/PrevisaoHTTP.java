package com.example.previsaodotempoapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class PrevisaoHTTP {

    //construtor http que vai ser utilizado para fazer a requisicao
    public static Retrofit create(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.open-meteo.com/v1/")
                .build();
    }
}
