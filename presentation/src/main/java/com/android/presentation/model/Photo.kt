package com.android.presentation.model

import android.os.Parcelable
import com.android.domain.model.PhotoEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    var albumId: Int,
    var id: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String) : Parcelable

fun PhotoEntity.toUI() : Photo = Photo(
    albumId = albumId,
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)