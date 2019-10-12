package com.pedromoniz.gistlistmvvm.presentation.gistDetailPage

import android.os.Bundle
import android.system.Os.close
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.pedromoniz.gistlistmvvm.R
import com.pedromoniz.gistlistmvvm.Utils.Failure
import com.pedromoniz.gistlistmvvm.presentation.gistListPage.GistListPageDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class GistDetailPage : Fragment(R.layout.gist_detail_page_fragment) {

    private val viewModel: GistDetailPageViewModel by viewModel()

    private val args: GistDetailPageArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.failure.observe(this, Observer { failure -> handleFailure(failure)})
        viewModel.loadSelectedGistDetail(args.gistId)
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> {
                this.view?.let { Snackbar.make(it,"Failed Network Connection", Snackbar.LENGTH_SHORT).show() }
            }
            is Failure.ServerError -> {
                this.view?.let { Snackbar.make(it,"Server Error", Snackbar.LENGTH_SHORT).show() }
            }
        }
    }
}
