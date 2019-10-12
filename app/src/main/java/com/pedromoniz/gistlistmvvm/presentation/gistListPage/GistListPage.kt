package com.pedromoniz.gistlistmvvm.presentation.gistListPage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pedromoniz.gistlistmvvm.R

class GistListPage : Fragment() {

    companion object {
        fun newInstance() = GistListPage()
    }

    private lateinit var viewModel: GistListPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gist_list_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GistListPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
