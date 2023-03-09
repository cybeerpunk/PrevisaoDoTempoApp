package com.example.previsaodotempoapp.api

import com.example.previsaodotempoapp.dto.ListDetalhesDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IPrevisaoREST {

    @GET("https://api.open-meteo.com/v1/forecast?latitude=-26.23&longitude=-51.09&hourly=temperature_2m,precipitation")
    fun getValuePrevisao(
    ): Call<ListDetalhesDTO>

    @GET("https://api.open-meteo.com/v1/forecast?hourly=temperature_2m,precipitation")
    fun getValueWithLocation(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): Call<ListDetalhesDTO>
}