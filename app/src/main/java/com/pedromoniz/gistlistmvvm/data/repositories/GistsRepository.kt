package com.pedromoniz.gistlistmvvm.data.repositories

import com.pedromoniz.gistlistmvvm.data.models.GistModel
import com.pedromoniz.gistlistmvvm.data.sources.GistDataSource
import com.pedromoniz.gistlistmvvm.domain.entities.GistEntity
import com.pedromoniz.gistlistmvvm.domain.gateways.GistsGateway
import java.lang.Exception
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class GistsRepository(
    private val GistLocalDataSource: GistDataSource,
    private val GistRemoteDataSource: GistDataSource
) : GistsGateway {

    private var cachedGists: ConcurrentMap<String, GistEntity>? = null

    override suspend fun getGist(gistId: String): GistEntity {
        //get from cache
        val gist = cachedGists?.get(gistId)
        if (gist != null)
            return gist
        //get from room
        try {
            val gistModel = GistLocalDataSource.getGistTaskId(gistId)
            return cacheGist(gistModel)
        } catch (exception: Exception) {
        }
        //get from services
        try {
            val gistModel = GistRemoteDataSource.getGistTaskId(gistId)
            refreshLocalDataSource(gistModel)
            return cacheGist(gistModel)
        } catch (exception: Exception) {
            throw exception
        }
    }

    override suspend fun getGists(): List<GistEntity> {
        val forcedFetch = false
        if(forcedFetch) {
            //get from cache
            if (!cachedGists.isNullOrEmpty())
                cachedGists?.let { gists ->
                    return gists.values.sortedBy { it.id }
                }
            //get from room
            try {
                val gistModels = GistLocalDataSource.getGists()
                if (!gistModels.isNullOrEmpty()) {
                    refreshCache(gistModels)
                    cachedGists?.let { gists ->
                        return gists.values.sortedBy { it.id }
                    }
                }
            } catch (exception: Exception) {
            }
        }

        //get from services
        try {
            val gistModels = GistRemoteDataSource.getGists()
            refreshLocalDataSource(gistModels)
            refreshCache(gistModels)
            cachedGists?.let { gists ->
                return gists.values.sortedBy { it.id }
            }
        } catch (exception: Exception) {
            throw exception
        }
        throw IllegalStateException()
    }

    private fun refreshCache(gists: List<GistModel>) {
        cachedGists?.clear()
        gists.sortedBy { it.id }.forEach {
            cacheGist(it)
        }
    }

    private suspend fun refreshLocalDataSource(gists: List<GistModel>) {
        GistLocalDataSource.deleteAllGists()
        for (gist in gists) {
            GistLocalDataSource.saveGist(gist)
        }
    }

    private suspend fun refreshLocalDataSource(gist: GistModel) {
        GistLocalDataSource.saveGist(gist)
    }

    private fun cacheGist(gist: GistModel): GistEntity {
        val cachedGist = GistEntity(gist.id,gist.description ?: "", gist.owner.avatarUrl ?: "")
        // Create if it doesn't exist.
        if (cachedGists == null) {
            cachedGists = ConcurrentHashMap()
        }
        cachedGists?.put(cachedGist.id, cachedGist)
        return cachedGist
    }
}