package com.pedromoniz.gistlistmvvm.di

import com.pedromoniz.gistlistmvvm.data.api.GisthubAPI
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val ApiModule = module {
    single(createdAtStart = false) { provideRetrofit()}
    single(createdAtStart = false) { get<Retrofit>().create(GisthubAPI::class.java) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}