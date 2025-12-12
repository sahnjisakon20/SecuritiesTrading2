package com.example.securitiestrading

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.cathaybk.passkey.di.getKoinModuleList
import android.app.Application

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@Application)
            modules(getKoinModuleList())
        }
    }
}