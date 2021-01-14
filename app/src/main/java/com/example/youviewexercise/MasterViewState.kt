package com.example.youviewexercise

import com.example.youviewexercise.models.Person

data class MasterViewState(
    val loadingError: Boolean = false,
    val persons: List<Person> = emptyList(),
    val loading: Boolean = false
)


