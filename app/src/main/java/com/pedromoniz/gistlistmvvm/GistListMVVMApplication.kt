package com.pedromoniz.gistlistmvvm

import android.app.Application
import com.pedromoniz.gistlistmvvm.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GistListMVVMApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin {
            androidContext(this@GistListMVVMApplication)
            modules(listOf(ApiModule, DatabaseModule,RepositoryModule ,InteractorModule ,ViewModelModule))
        }
    }
}