package com.pedromoniz.gistlistmvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pedromoniz.gistlistmvvm.data.database.GistDatabase.Companion.DB_VERSION
import com.pedromoniz.gistlistmvvm.data.models.GistModel

@Database(entities = [GistModel::class], version = DB_VERSION, exportSchema = false)
abstract class GistDatabase : RoomDatabase() {

    abstract fun gistDao(): GistDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "gist_list_mvvm.db"
        @Volatile
        private var INSTANCE: GistDatabase? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, GistDatabase::class.java, DB_NAME)
                .build()
    }

}