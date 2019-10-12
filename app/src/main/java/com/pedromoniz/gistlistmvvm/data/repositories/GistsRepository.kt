package com.pedromoniz.gistlistmvvm.data.repositories

import com.pedromoniz.gistlistmvvm.data.sources.GistDataSource
import com.pedromoniz.gistlistmvvm.domain.entities.GistEntity
import com.pedromoniz.gistlistmvvm.domain.gateways.GistsGateway

class GistsRepository(
    private val GistLocalDataSource: GistDataSource,
    private val GistRemoteDataSource: GistDataSource
) : GistsGateway {

    override suspend fun getGist(gistId: String): GistEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getGists(): List<GistEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}