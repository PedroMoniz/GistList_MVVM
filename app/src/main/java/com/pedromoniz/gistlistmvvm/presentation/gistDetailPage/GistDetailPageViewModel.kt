package com.pedromoniz.gistlistmvvm.presentation.gistDetailPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedromoniz.gistlistmvvm.Utils.BaseViewModel
import com.pedromoniz.gistlistmvvm.domain.entities.GistEntity
import com.pedromoniz.gistlistmvvm.domain.interactors.GetGistDetailUseCase

class GistDetailPageViewModel(
    val getGistDetailUseCase: GetGistDetailUseCase
) : BaseViewModel() {
    private val _gist = MutableLiveData<GistEntity>().apply { value = null }
    val gist: LiveData<GistEntity> = _gist

    fun loadSelectedGistDetail(gistId: String) {
        val params = GetGistDetailUseCase.Params(gistId)
        getGistDetailUseCase.invoke(viewModelScope, params) {
            it.either(
                ::handleFailure,
                ::handleGistDetail
            )
        }
    }

    private fun handleGistDetail(gist: GistEntity) {
        _gist.value = gist
    }
}
