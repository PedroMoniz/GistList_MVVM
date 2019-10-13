package com.pedromoniz.gistlistmvvm.data.api

import com.pedromoniz.gistlistmvvm.data.models.GistModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GisthubAPI {
    companion object {
        private const val PARAM_GIST_ID = "gistId"
        private const val GISTS = "/gists/public"
        private const val GIST = "/gists/:{$PARAM_GIST_ID}"
    }

    @GET(GISTS) suspend fun gists(): List<GistModel>
    @GET(GIST) suspend fun gist(@Path(PARAM_GIST_ID) gistId: String): GistModel
}