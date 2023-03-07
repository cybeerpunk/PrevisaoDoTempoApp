package com.example.previsaodotempoapp.exception;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;

public class BaseHTTPException extends Exception {

    public BaseHTTPException(String errorMessage) {
        super(errorMessage);
    }

    public static BaseHTTPException fromHTTPErrorBody(@Nullable ResponseBody responseBody) throws IOException {
        if(responseBody == null)
            throw new IOException("O servidor não retornou uma resposta válida. Contate o suporte do OJO.");
        try {
            BaseDMVCResponse response = new Gson().fromJson(responseBody.string(), BaseDMVCResponse.class);
            return new BaseHTTPException(response.message);
        } catch (Exception e) {
            throw new IOException("O servidor não retornou uma resposta válida. Contate o suporte do OJO.");
        }
    }

}