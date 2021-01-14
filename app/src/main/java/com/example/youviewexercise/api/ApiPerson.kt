package com.example.youviewexercise.api

import com.example.youviewexercise.models.Person
import com.google.gson.annotations.SerializedName

data class ApiPerson(
    @SerializedName("name") val name: ApiPersonName,
    @SerializedName("picture") val picture: ApiPicture,
    @SerializedName("email") val email: String,
    @SerializedName("location") val location: ApiLocation
) {
    fun toModel(): Person {
        return Person(
            title = name.title,
            firstName = name.firstName,
            lastName = name.lastName,
            email = email,
            thumbnailUrl = picture.thumbnailUrl,
            largeUrl = picture.largePictureUrl,
            location = location.toModel()
        )
    }
}

