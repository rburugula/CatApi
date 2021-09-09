package com.example.dogapi

import android.app.Application
import com.example.dogapi.di.DaggerAppComponent

class App: Application() {
    val appComponent = DaggerAppComponent.create()
}