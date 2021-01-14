package com.example.youviewexercise.models

import java.io.Serializable

data class Person(
    val title: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val thumbnailUrl: String,
    val largeUrl: String,
    val location: Location
) : Serializable