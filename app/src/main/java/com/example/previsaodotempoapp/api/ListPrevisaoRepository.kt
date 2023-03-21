package com.example.previsaodotempoapp.api

import android.content.Context
import com.example.previsaodotempoapp.dto.ListDetalhesDTO
import com.example.previsaodotempoapp.dto.ObjeticLocationDTO
import com.example.previsaodotempoapp.dto.StoreClimaDTO
import com.example.previsaodotempoapp.exception.PrevisaoException
import com.example.previsaodotempoapp.store.AddressPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListPrevisaoRepository (val context: Context) {


   /* fun getValuePrevisao(): ListDetalhesDTO {
        val response = PrevisaoREST()
            .getValuePrevisao()
            .execute()
        if (response.code() != 200 && response.code() != 201)
            throw PrevisaoException.fromHTTPErrorBody(response.errorBody())
        return  response.body()!!
    }*/


    // onde realiza a requisicao, intermediando a activity ou o fragment da interface
    fun getValueWithLocation(aLatitude: String, aLongitude: String): ListDetalhesDTO {
        val response = PrevisaoREST()
            .getValueWithLocation(aLatitude, aLongitude)
            .execute()
        if (response.code() != 200 && response.code() != 201)
            throw PrevisaoException.fromHTTPErrorBody(response.errorBody())
        return  response.body()!!

    }

    fun getNameOfLocation(aLat: String, aLong: String): ObjeticLocationDTO {
        val response = PrevisaoREST()
            .getNameOfLocation(aLat, aLong)
            .execute()
        if (response.code() != 200 && response.code() != 201)
            throw PrevisaoException.fromHTTPErrorBody(response.errorBody())
        return  response.body()!!

    }
     // adicionar as coisas no cache
    fun storeClima(storeClimaDTO: StoreClimaDTO){
        CoroutineScope(Dispatchers.IO).launch {
            AddressPreference(context).store(storeClimaDTO)
        }


    }
   // limpar o cache
    fun clearData(){
        CoroutineScope(Dispatchers.IO).launch {
            AddressPreference(context).clearData()
        }
    }
}