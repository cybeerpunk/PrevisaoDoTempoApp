package com.example.previsaodotempoapp.api

import com.example.previsaodotempoapp.dto.ListDetalhesDTO
import retrofit2.Call
import retrofit2.http.GET

interface IPrevisaoREST {

    @GET("https://api.open-meteo.com/v1/forecast?latitude=-26.23&longitude=-51.09&hourly=temperature_2m,precipitation/")
    fun getValuePrevisao(

    ): Call<ListDetalhesDTO>
}