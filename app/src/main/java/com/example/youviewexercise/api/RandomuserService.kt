package com.example.youviewexercise.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RandomUserService {
    fun create(): RandomUserApiInterface {
        val builder = OkHttpClient.Builder()

        val client = builder.build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://randomuser.me/")
            .client(client)
            .build()

        return retrofit.create(RandomUserApiInterface::class.java)
    }
}