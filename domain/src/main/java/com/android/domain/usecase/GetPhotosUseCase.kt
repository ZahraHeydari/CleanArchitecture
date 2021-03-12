package com.android.domain.usecase

import com.android.domain.base.SingleUseCase
import com.android.domain.model.PhotoEntity
import com.android.domain.repository.PhotosRepository

class GetPhotosUseCase(private val photosRepository: PhotosRepository)
    : SingleUseCase<List<PhotoEntity>?, Boolean>() {

    override suspend fun run(params: Boolean?): List<PhotoEntity>? {
        return params?.let { photosRepository.getPhotoList(it) }
    }

}