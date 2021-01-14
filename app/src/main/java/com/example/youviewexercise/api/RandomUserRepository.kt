package com.example.youviewexercise.api

import com.example.youviewexercise.models.Person
import javax.inject.Singleton

interface RandomUserRepository {
    suspend fun getRandomUsers(): List<Person>
}

@Singleton
class RandomUserRepositoryImpl constructor(
    private val api: RandomUserApiInterface
) : RandomUserRepository {

    override suspend fun getRandomUsers(): List<Person> {
        return api.getRandomUsers().results.map {
            it.toModel()
        }
    }

}