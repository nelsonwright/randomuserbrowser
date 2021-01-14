package com.example.youviewexercise.api

import com.google.gson.annotations.SerializedName

data class ApiResult(
    @SerializedName("results") val results: List<ApiPerson>
)