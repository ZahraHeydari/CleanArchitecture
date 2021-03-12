package com.android.data.source.local

import com.android.data.model.PhotoResponse

interface PhotosLocalDataSource {

    suspend fun getPhotos(): List<PhotoResponse>?

    suspend fun insertPhotoList(list: List<PhotoResponse>?)

}