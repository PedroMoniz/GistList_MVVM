package com.pedromoniz.gistlistmvvm.presentation.gistListPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

import com.pedromoniz.gistlistmvvm.R
import com.pedromoniz.gistlistmvvm.Utils.Failure
import com.pedromoniz.gistlistmvvm.domain.interactors.GetGistsTemplatesUseCase
import kotlinx.android.synthetic.main.gist_list_page_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GistListPage : Fragment(R.layout.gist_list_page_fragment) {

    private val viewModel: GistListPageViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.failure.observe(this, Observer { failure -> handleFailure(failure) })
        setupListAdapter()
        viewModel.loadGists()
    }

    private fun setupListAdapter() {
        val adapter = GistListAdapter(viewModel)
        gistListPageFragmenteRecyclerView.adapter = adapter
        viewModel.gists.observe(this, Observer {
            adapter.setData(if (it.isNullOrEmpty()) emptyList() else it.toList())
        })
        // Navigate to detail view with the image's url to display
        viewModel.navigateToDetail.observe(this, Observer { gistId ->
            val action = GistListPageDirections.actionGistListPageToGistDetailPage(gistId)
            findNavController().navigate(action)
        })
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is GetGistsTemplatesUseCase.GetGistsTemplatesFailure -> {
                this.view?.let {
                    failure.exception.message?.let { message ->
                        Snackbar.make(
                            it,
                            message,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            is Failure.NetworkConnection -> {
                this.view?.let {
                    Snackbar.make(
                        it,
                        "Failed Network Connection",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            is Failure.ServerError -> {
                this.view?.let { Snackbar.make(it, "Server Error", Snackbar.LENGTH_SHORT).show() }
            }
        }
    }
}
