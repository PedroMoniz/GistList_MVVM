package com.pedromoniz.gistlistmvvm.domain.entities

data class GistEntity (var id:String = "", var title: String = "", var description: String = "", var imageUrl: String = ""){
    fun isValid() : Boolean { return id.isEmpty() || title.isEmpty() || description.isEmpty() }
    fun hasImage() : Boolean { return imageUrl.isEmpty()  }
}
