package com.example.youviewexercise.di

import com.example.youviewexercise.MasterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, RandomUserRepositoryModule::class, NetworkModule::class, IODispatcherModule::class,
        MainDispatcherModule::class]
)

interface AppComponent {
    fun inject(target: MasterFragment)
}