package com.pedromoniz.gistlistmvvm.di

import com.pedromoniz.gistlistmvvm.data.repositories.GistsRepository
import com.pedromoniz.gistlistmvvm.data.sources.GistDataSource
import com.pedromoniz.gistlistmvvm.data.sources.local.GistLocalDataSource
import com.pedromoniz.gistlistmvvm.data.sources.remote.GistRemoteDataSource
import com.pedromoniz.gistlistmvvm.domain.gateways.GistsGateway
import com.pedromoniz.gistlistmvvm.providers.NetworkHandler
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module


val RepositoryModule = module {
    //sources local
    single<GistDataSource>(named("GistLocalDataSource")) { GistLocalDataSource(get()) }
    //sources remote
    single<GistDataSource>(named("GistRemoteDataSource")) { GistRemoteDataSource(get(), NetworkHandler(androidApplication())) }

    single<GistsGateway>(createdAtStart = true) {
        GistsRepository(
            get(named("GistLocalDataSource")), get(named("GistRemoteDataSource"))
        )
    }
}