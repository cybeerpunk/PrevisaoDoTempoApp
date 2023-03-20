package com.example.previsaodotempoapp.api;

import com.example.previsaodotempoapp.dto.ListDetalhesDTO;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Retrofit;

abstract public class BaseREST<T> {
    protected T service;

    protected Retrofit retrofit;

    protected BaseREST(Class<T> classType) {
        this.retrofit = PrevisaoHTTP.create();
        this.service = retrofit.create(classType);
    }

}