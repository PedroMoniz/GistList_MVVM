package com.pedromoniz.gistlistmvvm.data.sources.local

import com.pedromoniz.gistlistmvvm.data.database.GistDao
import com.pedromoniz.gistlistmvvm.data.models.GistModel
import com.pedromoniz.gistlistmvvm.data.sources.GistDataSource

class GistLocalDataSource constructor(
    private val gistDao: GistDao
) : GistDataSource {
    override suspend fun deleteAllGists() {
        gistDao.deleteGists()
    }

    override suspend fun saveGist(gist: GistModel) {
        gistDao.insertGist(gist)
    }

    override suspend fun getGistTaskId(gistId: String): GistModel {
        val gist = gistDao.getGist(gistId)
        if (gist != null) {
            return gist
        } else {
            throw Exception("Gist not found!")
        }
    }

    override suspend fun getGists(): List<GistModel> {
        return gistDao.getGists()
    }

}