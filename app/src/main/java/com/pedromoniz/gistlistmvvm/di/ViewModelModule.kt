package com.pedromoniz.gistlistmvvm.di

import com.pedromoniz.gistlistmvvm.presentation.gistDetailPage.GistDetailPageViewModel
import com.pedromoniz.gistlistmvvm.presentation.gistListPage.GistListPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ViewModelModule = module {
    viewModel {
        GistListPageViewModel(
            get()
        )
    }
    viewModel {
        GistDetailPageViewModel(
            get()
        )
    }
}

