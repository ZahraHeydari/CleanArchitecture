package com.android.data.source.local

import com.android.data.model.PhotoResponse
import com.android.data.source.local.base.AppDatabase


class PhotosLocalDataSourceImp(private val appDatabase: AppDatabase) : PhotosLocalDataSource {

    override suspend fun getPhotos(): List<PhotoResponse>? {
        return appDatabase.photoDao.loadAll()
    }

    override suspend fun insertPhotoList(list: List<PhotoResponse>?) {
        return appDatabase.photoDao.insertAll(list)
    }

}