package com.pedromoniz.gistlistmvvm.domain.entities

data class GistEntity (var id:String = "", var description: String = "", var imageUrl: String = ""){
    fun isValid() : Boolean { return id.isEmpty() || description.isEmpty() }
    fun hasImage() : Boolean { return imageUrl.isNotEmpty()
    }
}
