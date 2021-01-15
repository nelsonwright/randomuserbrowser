package com.example.youviewexercise.di

import com.example.youviewexercise.api.RandomUserRepository
import com.example.youviewexercise.api.RandomUserRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RandomUserRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsRandomUserRepository(randomUserRepository: RandomUserRepositoryImpl): RandomUserRepository
}