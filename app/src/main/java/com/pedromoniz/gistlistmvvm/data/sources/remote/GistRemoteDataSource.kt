package com.pedromoniz.gistlistmvvm.data.sources.remote

import com.pedromoniz.gistlistmvvm.data.api.GisthubAPI
import com.pedromoniz.gistlistmvvm.data.models.GistModel
import com.pedromoniz.gistlistmvvm.data.models.Owner
import com.pedromoniz.gistlistmvvm.data.sources.GistDataSource
import com.pedromoniz.gistlistmvvm.domain.exceptions.NetworkConnectionError
import com.pedromoniz.gistlistmvvm.domain.exceptions.ServerError
import com.pedromoniz.gistlistmvvm.providers.NetworkHandler

class GistRemoteDataSource constructor(
    private val gisthubAPI: GisthubAPI,
    private val networkHandler: NetworkHandler
) : GistDataSource {

    override suspend fun deleteAllGists() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun saveGist(gist: GistModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getGistTaskId(gistId: String): GistModel =
        when (networkHandler.isConnected()) {
            true -> request(gisthubAPI.gist(gistId), GistModel())
            false -> throw NetworkConnectionError()
        }

    override suspend fun getGists(): List<GistModel> =
        when (networkHandler.isConnected()) {
            true -> request(gisthubAPI.gists(), emptyList())
            false -> throw NetworkConnectionError()
        }

    private fun <R> request(response: R, default: R): R {

        //todo, add a transform and use response objects. then just use response 'to' method

        return try {
            response ?: default
        } catch (exception: Throwable) {
            throw ServerError()
        }
    }
}