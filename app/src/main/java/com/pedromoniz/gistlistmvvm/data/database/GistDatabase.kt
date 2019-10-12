package com.pedromoniz.gistlistmvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedromoniz.gistlistmvvm.data.models.GistModel

@Database(entities = [GistModel::class], version = 1, exportSchema = false)
abstract class GistDatabase : RoomDatabase() {

    abstract fun gistDao(): GistDao
}