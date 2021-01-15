package com.example.youviewexercise

import android.app.Application
import com.example.youviewexercise.di.AppComponent
import com.example.youviewexercise.di.DaggerAppComponent

class YouViewApplication : Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent: AppComponent = DaggerAppComponent.create()
}