package com.example.previsaodotempoapp.api

import com.example.previsaodotempoapp.dto.ListDetalhesDTO
import retrofit2.Call

class PrevisaoREST : BaseREST<IPrevisaoREST>(IPrevisaoREST::class.java){

    fun getValuePrevisao(): Call<ListDetalhesDTO> {
        return service.getValuePrevisao()
    }
}