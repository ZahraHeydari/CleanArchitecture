package com.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.domain.model.PhotoEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
data class PhotoResponse(
    var albumId: Int,
    @PrimaryKey var id: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String){


    fun toDomain(): PhotoEntity =
        PhotoEntity(
            albumId = albumId,
            id = id,
            title = title,
            url = url,
            thumbnailUrl = thumbnailUrl
        )
}

