package com.example.youviewexercise.api.apimodels

import com.google.gson.annotations.SerializedName

data class ApiPersonName(
    @SerializedName("title") val title: String = "",
    @SerializedName("first") val firstName: String = "",
    @SerializedName("last") val lastName: String = ""
)