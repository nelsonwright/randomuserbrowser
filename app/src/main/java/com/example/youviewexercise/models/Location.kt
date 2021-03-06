package com.example.youviewexercise.models

import java.io.Serializable

data class Location(
    val streetNumber: String,
    val streetName: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String
) : Serializable