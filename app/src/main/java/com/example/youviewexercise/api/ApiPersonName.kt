package com.example.youviewexercise.api

import com.google.gson.annotations.SerializedName

data class ApiPersonName(
    @SerializedName("title") val title: String = "",
    @SerializedName("first") val firstName: String = "",
    @SerializedName("last") val lastName: String = ""
)