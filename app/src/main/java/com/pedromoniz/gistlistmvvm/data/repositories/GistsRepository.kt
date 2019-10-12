package com.pedromoniz.gistlistmvvm.data.repositories

import com.pedromoniz.gistlistmvvm.data.sources.GistDataSource
import com.pedromoniz.gistlistmvvm.domain.gateways.GistsGateway

class GistsRepository(
    private val GistLocalDataSource: GistDataSource,
    private val GistRemoteDataSource: GistDataSource
) : GistsGateway {

}