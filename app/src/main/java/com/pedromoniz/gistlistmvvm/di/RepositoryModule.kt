package com.pedromoniz.gistlistmvvm.di

import com.pedromoniz.gistlistmvvm.data.repositories.GistsRepository
import com.pedromoniz.gistlistmvvm.domain.gateways.GistsGateway
import org.koin.core.qualifier.named
import org.koin.dsl.module


val RepositoryModule = module {
    single<GistsGateway>(createdAtStart = true) {
        GistsRepository(
            get(named("GistLocalDataSource")), get(named("GistRemoteDataSource"))
        )
    }
}