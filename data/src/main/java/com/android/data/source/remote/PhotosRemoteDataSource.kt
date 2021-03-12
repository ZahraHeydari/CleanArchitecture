package com.android.data.source.remote

import com.android.data.model.PhotoResponse

interface PhotosRemoteDataSource {

    suspend fun getPhotos() : List<PhotoResponse>
}