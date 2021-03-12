package com.android.domain.repository

import com.android.domain.model.PhotoEntity

interface PhotosRepository {

    suspend fun getPhotoList(networkAvailability : Boolean) : List<PhotoEntity>?
}