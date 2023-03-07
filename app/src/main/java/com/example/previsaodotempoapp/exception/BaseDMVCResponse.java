package com.example.previsaodotempoapp.exception;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;

public final class BaseDMVCResponse {


    public String message;

    public static BaseDMVCResponse fromResponseBody(ResponseBody responseBody) throws IOException {
        return new Gson().fromJson(responseBody.string(), BaseDMVCResponse.class);
    }

}