package com.pedromoniz.gistlistmvvm.domain.gateways

import com.pedromoniz.gistlistmvvm.domain.entities.GistEntity

interface GistsGateway {
    suspend fun getGists() : List<GistEntity>
    suspend fun getGist(gistId : String) : GistEntity
}