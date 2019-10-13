package com.pedromoniz.gistlistmvvm.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pedromoniz.gistlistmvvm.data.models.GistModel


@Dao
interface GistDao {
    /**
     * Select all gists from the gists table.
     *
     * @return all gists.
     */
    @Query("SELECT * FROM gistModels")
    suspend fun getGists(): List<GistModel>

    /**
     * Select a gist by id.
     *
     * @param gistId the gist id.
     * @return the task with taskId.
     */
    @Query("SELECT * FROM gistModels WHERE entryid = :gistId")
    suspend fun getGist(gistId: String): GistModel?

    /**
     * Insert a gist in the database. If the gist already exists, replace it.
     *
     * @param gist the gist to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGist(gist: GistModel)

    /**
     * Delete all tasks.
     */
    @Query("DELETE FROM gistModels")
    suspend fun deleteGists()
}