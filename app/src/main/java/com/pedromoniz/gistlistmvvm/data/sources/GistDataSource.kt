package com.pedromoniz.gistlistmvvm.data.sources

import com.pedromoniz.gistlistmvvm.data.models.GistModel

interface GistDataSource {

    suspend fun getGists(): List<GistModel>

    suspend fun getGistTaskId(gistId: String): GistModel

    suspend fun saveGist(gist: GistModel)

    suspend fun deleteAllGists()
}