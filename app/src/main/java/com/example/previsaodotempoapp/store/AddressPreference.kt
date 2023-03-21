package com.example.previsaodotempoapp.store

import android.content.Context
import android.content.SharedPreferences
import com.example.previsaodotempoapp.dto.StoreClimaDTO
import com.google.gson.Gson

class AddressPreference (mContext: Context){
    private var mSharedPreference: SharedPreferences? = null

    //padrão para criar um SharedPreferences

    companion object{
        const val PREFERENCE_KEY = "app-previsao-do-tempo-shared-preferences"
        const val CONFIG_INDEX = "previsao-do-tempo"
    }

    init {
        mSharedPreference = mContext.getSharedPreferences(PREFERENCE_KEY,Context.MODE_PRIVATE)
    }

    fun getEditor(): SharedPreferences.Editor{
        return mSharedPreference!!.edit()
    }

    fun store(storeClimaDTO: StoreClimaDTO){
        this.getEditor().putString(CONFIG_INDEX, Gson().toJson(storeClimaDTO)).commit()
    }

    fun clearData(){
        this.getEditor().clear().apply()
    }

    fun get(): StoreClimaDTO {
        return Gson().fromJson(
            mSharedPreference!!.getString(CONFIG_INDEX, null),
            StoreClimaDTO::class.java
        )?: return StoreClimaDTO()

    }
}