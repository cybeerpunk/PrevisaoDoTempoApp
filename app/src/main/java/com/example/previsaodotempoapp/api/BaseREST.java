package com.example.previsaodotempoapp.api;

import retrofit2.Retrofit;

abstract public class BaseREST<T> {
    protected T service;

    protected Retrofit retrofit;

    protected BaseREST(Class<T> classType){
        this.retrofit = PrevisaoHTTP.create();
        this.service = retrofit.create(classType);
    }
}
