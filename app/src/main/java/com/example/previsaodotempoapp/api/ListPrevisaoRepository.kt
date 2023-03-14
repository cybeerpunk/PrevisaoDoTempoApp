package com.example.previsaodotempoapp.api

import com.example.previsaodotempoapp.dto.ListDetalhesDTO
import com.example.previsaodotempoapp.exception.PrevisaoException

class ListPrevisaoRepository {

   /* fun getValuePrevisao(): ListDetalhesDTO {
        val response = PrevisaoREST()
            .getValuePrevisao()
            .execute()
        if (response.code() != 200 && response.code() != 201)
            throw PrevisaoException.fromHTTPErrorBody(response.errorBody())
        return  response.body()!!
    }*/

    fun getValueWithLocation(aLatitude: String, aLongitude: String): ListDetalhesDTO {
        val response = PrevisaoREST()
            .getValueWithLocation(aLatitude, aLongitude)
            .execute()
        if (response.code() != 200 && response.code() != 201)
            throw PrevisaoException.fromHTTPErrorBody(response.errorBody())
        return  response.body()!!

    }
}