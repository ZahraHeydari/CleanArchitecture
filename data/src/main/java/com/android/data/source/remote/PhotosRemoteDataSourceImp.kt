package com.android.data.source.remote

import com.android.data.model.PhotoResponse
import com.android.data.source.remote.base.ApiService


class PhotosRemoteDataSourceImp(private val apiService: ApiService) : PhotosRemoteDataSource {

    override suspend fun getPhotos() : List<PhotoResponse>{
        return apiService.getPhotos()
    }
}