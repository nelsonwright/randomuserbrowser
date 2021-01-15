package com.example.youviewexercise.api.apimodels

import com.google.gson.annotations.SerializedName

data class ApiResult(
    @SerializedName("results") val results: List<ApiPerson>
)