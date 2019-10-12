package com.pedromoniz.gistlistmvvm.di

import com.pedromoniz.gistlistmvvm.data.api.GisthubAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val ApiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(GisthubAPI::class.java) }
}