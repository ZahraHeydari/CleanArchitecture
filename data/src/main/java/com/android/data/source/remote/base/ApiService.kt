package com.android.data.source.remote.base

import com.android.data.model.PhotoResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/photos")
    suspend fun getPhotos(): List<PhotoResponse>
}