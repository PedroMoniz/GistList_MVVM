package com.pedromoniz.gistlistmvvm.presentation.gistDetailPage

import android.os.Bundle
import android.system.Os.close
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.pedromoniz.gistlistmvvm.R
import com.pedromoniz.gistlistmvvm.Utils.Failure
import com.pedromoniz.gistlistmvvm.databinding.GistDetailPageFragmentBinding
import com.pedromoniz.gistlistmvvm.presentation.gistListPage.GistListPageDirections
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class GistDetailPage : Fragment() {
    private lateinit var viewDataBinding: GistDetailPageFragmentBinding

    private val viewModel: GistDetailPageViewModel by viewModel()

    private val args: GistDetailPageArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.gist_detail_page_fragment, container, false)
        viewDataBinding = GistDetailPageFragmentBinding.bind(view).apply {
            viewmodel = viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.loadSelectedGistDetail(args.gistId)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.failure.observe(this, Observer { failure -> handleFailure(failure)})
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
