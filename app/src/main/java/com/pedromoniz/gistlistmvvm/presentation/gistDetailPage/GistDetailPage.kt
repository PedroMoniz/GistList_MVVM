package com.pedromoniz.gistlistmvvm.presentation.gistDetailPage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pedromoniz.gistlistmvvm.R

class GistDetailPage : Fragment() {

    companion object {
        fun newInstance() = GistDetailPage()
    }

    private lateinit var viewModel: GistDetailPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gist_detail_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GistDetailPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
