package com.example.youviewexercise.api

import com.example.youviewexercise.api.apimodels.ApiResult
import retrofit2.http.GET
import retrofit2.http.Headers

interface RandomUserApiInterface {
    @Headers("Cache-Control: max-age=120")
    @GET("api/?results=200&seed=yv")
    suspend fun getRandomUsers(): ApiResult
}
