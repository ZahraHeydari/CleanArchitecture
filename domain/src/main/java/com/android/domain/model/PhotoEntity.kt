package com.android.domain.model

data class PhotoEntity(
    var albumId: Int,
    var id: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String)