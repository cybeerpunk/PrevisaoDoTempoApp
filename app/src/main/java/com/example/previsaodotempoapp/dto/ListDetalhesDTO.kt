package com.example.previsaodotempoapp.dto

 data class ListDetalhesDTO(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val hourly: TimeDTO = TimeDTO(),
    val temperature_2m: List<Double>? = null,
    val precipitation: List<Double>? = null
)
