package com.example.week11

import android.app.Application
import com.example.week11.dependenciesInjection.ContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp
    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) // membuat instance
        // instance adalah object yang dibuat dari class
    }
}