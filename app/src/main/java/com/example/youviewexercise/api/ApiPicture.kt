package com.example.youviewexercise.api

import com.google.gson.annotations.SerializedName

data class ApiPicture(
    @SerializedName("large") val largePictureUrl: String,
    @SerializedName("medium") val mediumPictureUrl: String,
    @SerializedName("thumbnail") val thumbnailUrl: String,
)