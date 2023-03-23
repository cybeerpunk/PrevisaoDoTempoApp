package com.example.previsaodotempoapp.api

import com.example.previsaodotempoapp.dto.ListDetalhesDTO
import com.example.previsaodotempoapp.dto.ObjeticLocationDTO
import com.example.previsaodotempoapp.dto.ReturnPostDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface IPrevisaoREST {

    //coloca as url's de requisição
    @GET("https://api.open-meteo.com/v1/forecast?latitude=-26.23&longitude=-51.09&hourly=temperature_2m,precipitation")
    fun getValuePrevisao(
    ): Call<ListDetalhesDTO>

    @GET("https://api.open-meteo.com/v1/forecast?hourly=temperature_2m,precipitation")
    fun getValueWithLocation(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): Call<ListDetalhesDTO>

    @GET("https://nominatim.openstreetmap.org/reverse")
    fun getNameOfLocation(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("format") format: String
    ): Call<ObjeticLocationDTO>

    @POST("https://jsonplaceholder.typicode.com/todos/")
    fun postTodos(): Call<ReturnPostDTO>

    @PUT ("https://jsonplaceholder.typicode.com/posts/1")
    fun putTodos(): Call<ReturnPostDTO>

}