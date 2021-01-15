package com.example.youviewexercise.di

import com.example.youviewexercise.api.RandomUserApiInterface
import com.example.youviewexercise.api.RandomUserService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRandomUserApiInterface(): RandomUserApiInterface {
        return RandomUserService.create()
    }
}