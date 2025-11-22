package com.example.shoeprotector

import android.app.Application
import com.example.shoeprotector.di.apiModule
import com.example.shoeprotector.di.appModule
import com.example.shoeprotector.di.networkModule
import com.example.shoeprotector.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                appModule,
                apiModule,
                networkModule,
                repositoryModule
            )
        }
    }
}