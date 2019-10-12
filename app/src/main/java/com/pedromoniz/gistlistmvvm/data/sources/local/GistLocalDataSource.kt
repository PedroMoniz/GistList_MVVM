package com.pedromoniz.gistlistmvvm.data.sources.local

import com.pedromoniz.gistlistmvvm.data.database.GistDao
import com.pedromoniz.gistlistmvvm.data.sources.GistDataSource

class GistLocalDataSource constructor(
    private val gistDao: GistDao
) : GistDataSource {

}