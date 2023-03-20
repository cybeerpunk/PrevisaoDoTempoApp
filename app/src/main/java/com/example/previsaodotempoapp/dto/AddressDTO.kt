package com.example.previsaodotempoapp.dto

data class AddressDTO(
    val amenity: String = "",
    val road: String = "",
    val suburb: String = "",
    val city: String = "",
    val municipality: String = "",
    val region: String = "",
    val state: String = "",
    val postcode: String = "",
    val country: String = "",
    val country_code: String = ""
)
