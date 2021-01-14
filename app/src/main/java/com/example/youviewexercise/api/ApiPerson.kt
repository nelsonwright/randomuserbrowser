package com.example.youviewexercise.api

import com.example.youviewexercise.models.Person
import com.google.gson.annotations.SerializedName

data class ApiResult(
    @SerializedName("results") val results: List<ApiPerson>,
)

data class ApiPerson(
    @SerializedName("name") val name: ApiPersonName,
    @SerializedName("picture") val picture: ApiPicture
) {
    fun toModel(): Person {
        return Person(
            title = name.title,
            firstName = name.firstName,
            lastName = name.lastName
        )
    }
}

data class ApiPersonName(
    @SerializedName("title") val title: String = "",
    @SerializedName("first") val firstName: String = "",
    @SerializedName("last") val lastName: String = ""
)

data class ApiPicture(
    @SerializedName("large") val largePictureUrl: String,
    @SerializedName("medium") val mediumPictureUrl: String,
    @SerializedName("thumbnail") val thumbnailUrl: String,
)