package com.example.previsaodotempoapp.api

import com.example.previsaodotempoapp.dto.ListDetalhesDTO
import com.example.previsaodotempoapp.dto.ObjeticLocationDTO
import retrofit2.Call

class PrevisaoREST : BaseREST<IPrevisaoREST>(IPrevisaoREST::class.java){


    //utilizado para decidir o service e base url na requisicao
    fun getValuePrevisao(): Call<ListDetalhesDTO> {
        return service.getValuePrevisao()
    }

    fun getValueWithLocation(latitude: String, longitude: String): Call<ListDetalhesDTO> {
        return service.getValueWithLocation(latitude, longitude)
    }

    fun getNameOfLocation(lat: String, long: String): Call<ObjeticLocationDTO> {
        return service.getNameOfLocation(lat, long, "json")
    }

}


