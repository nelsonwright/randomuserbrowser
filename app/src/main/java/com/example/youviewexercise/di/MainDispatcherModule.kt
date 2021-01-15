package com.example.youviewexercise.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Singleton

@Module
class MainDispatcherModule {

    @Provides
    @Singleton
    fun provideMainDispatcher(): MainCoroutineDispatcher {
        return Dispatchers.Main
    }
}