package com.pedromoniz.gistlistmvvm.di

import androidx.room.Room
import com.pedromoniz.gistlistmvvm.data.database.GistDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val DatabaseModule = module {
    single { GistDatabase.getInstance(androidApplication()) }
    single(createdAtStart = true) { get<GistDatabase>().gistDao() }
}