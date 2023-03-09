package com.example.previsaodotempoapp.dto

 data class ListDetalhesDTO(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val hourly: TimeDTO = TimeDTO()

)
