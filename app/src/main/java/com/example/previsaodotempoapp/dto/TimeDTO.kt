package com.example.previsaodotempoapp.dto

data class TimeDTO(
    val time: List<String> = emptyList(),
    val temperature_2m: List<Double> = emptyList(),
    val precipitation: List<Double> = emptyList()
)
