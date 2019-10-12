package com.pedromoniz.gistlistmvvm.di

import com.pedromoniz.gistlistmvvm.domain.interactors.GetGistDetailUseCase
import com.pedromoniz.gistlistmvvm.domain.interactors.GetGistsTemplatesUseCase
import com.pedromoniz.gistlistmvvm.domain.interactors.UseCase
import org.koin.dsl.module

val InteractorModule = module {
    //GistUseCases
    factory {
        GetGistDetailUseCase(
            get()
        )
    }
    factory {
        GetGistsTemplatesUseCase(
            get()
        )
    }
}