package com.pedromoniz.gistlistmvvm.presentation.gistListPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pedromoniz.gistlistmvvm.Utils.BaseViewModel
import com.pedromoniz.gistlistmvvm.Utils.HybridLiveEvent
import com.pedromoniz.gistlistmvvm.Utils.UseCase.None
import com.pedromoniz.gistlistmvvm.domain.entities.GistEntity
import com.pedromoniz.gistlistmvvm.domain.interactors.GetGistsTemplatesUseCase

class GistListPageViewModel(
    val getGistsTemplatesUseCase: GetGistsTemplatesUseCase
) : BaseViewModel() {

    val navigateToDetail = HybridLiveEvent<String>()

    private val _gists = MutableLiveData<List<GistEntity>>().apply { value = emptyList() }
    val gists: LiveData<List<GistEntity>> = _gists

    fun loadGists() {
        getGistsTemplatesUseCase.invoke(viewModelScope, None()) {
            it.either(
                ::handleFailure,
                ::handleGists
            )
        }

    }

    private fun handleGists(gists: List<GistEntity>) {
        _gists.value = gists
    }

    fun onGistSelected(gistId: String) {
        navigateToDetail.value = gistId
    }
}
