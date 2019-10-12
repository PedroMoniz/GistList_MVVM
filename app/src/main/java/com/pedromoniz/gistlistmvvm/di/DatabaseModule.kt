package com.pedromoniz.gistlistmvvm.di

import androidx.room.Room
import com.pedromoniz.gistlistmvvm.data.database.GistDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val DatabaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), GistDatabase::class.java, "Gists.db")
            .build()
    }
    single(createdAtStart = false) { get<GistDatabase>().gistDao() }
}