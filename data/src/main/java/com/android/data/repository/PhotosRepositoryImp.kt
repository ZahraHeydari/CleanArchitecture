package com.android.data.repository


import com.android.data.source.local.PhotosLocalDataSource
import com.android.data.source.remote.PhotosRemoteDataSource
import com.android.domain.model.PhotoEntity
import com.android.domain.repository.PhotosRepository

/**
 * This class is responsible to choose a source for fetching data
 * [photosLocalDataSource] to access/modify local data
 * [photosRemoteDataSource] to access/update remote data
 *
 * @author ZARA
 * */
class PhotosRepositoryImp constructor(
    private val photosRemoteDataSource: PhotosRemoteDataSource,
    private val photosLocalDataSource: PhotosLocalDataSource
) : PhotosRepository {

    override suspend fun getPhotoList(networkAvailability: Boolean): List<PhotoEntity>? {
        if (networkAvailability) {
            photosRemoteDataSource.getPhotos().also {
                photosLocalDataSource.insertPhotoList(it)
                return it.map { photoResponse ->
                    photoResponse.toDomain()
                }
            }

        } else return photosLocalDataSource.getPhotos()?.map {
            it.toDomain()
        }

    }
}