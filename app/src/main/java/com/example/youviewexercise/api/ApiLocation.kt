package com.example.youviewexercise.api

import com.example.youviewexercise.models.Location
import com.google.gson.annotations.SerializedName

data class ApiLocation(
    @SerializedName("street") val street: ApiStreet,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("postcode") val postcode: String
) {
    fun toModel(): Location {
        return Location(
            streetNumber = street.number,
            streetName = street.name,
            city = city,
            state = state,
            country = country,
            postcode = postcode
        )
    }
}