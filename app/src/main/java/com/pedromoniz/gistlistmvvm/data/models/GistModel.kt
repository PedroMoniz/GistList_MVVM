package com.pedromoniz.gistlistmvvm.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*


@Entity(tableName = "gistModels")
data class GistModel @JvmOverloads constructor(
    @ColumnInfo(name = "description") var description: String? = "",
    @ColumnInfo(name = "created_at") var created_at: String? = "",
    @ColumnInfo(name = "updated_at") var updated_at: String? = "",
    @Embedded var owner: Owner = Owner(),
    @PrimaryKey @ColumnInfo(name = "entryId") var id: String = UUID.randomUUID().toString()
)

data class Owner(
    @SerializedName("id") val id: String = UUID.randomUUID().toString(),
    @SerializedName("avatar_url") val avatarUrl: String? = "",
    @SerializedName("url") val url: String? = "",
    @SerializedName("type") val type: String? = ""
)