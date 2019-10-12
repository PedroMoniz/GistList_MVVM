package com.pedromoniz.gistlistmvvm

import android.app.Application
import com.pedromoniz.gistlistmvvm.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PapersoftApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin {
            androidContext(this@PapersoftApplication)
            modules(listOf(ApiModule, DatabaseModule,RepositoryModule ,InteractorModule ,ViewModelModule))
        }
    }
}