package com.pedromoniz.gistlistmvvm.data.sources.remote

import com.pedromoniz.gistlistmvvm.data.api.GisthubAPI
import com.pedromoniz.gistlistmvvm.data.sources.GistDataSource

class GistRemoteDataSource constructor(
    private val gisthubAPI: GisthubAPI
) : GistDataSource {

}