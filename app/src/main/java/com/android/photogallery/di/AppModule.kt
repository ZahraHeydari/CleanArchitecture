package com.android.photogallery.di

import com.android.data.repository.PhotosRepositoryImp
import com.android.data.source.local.PhotosLocalDataSource
import com.android.data.source.local.PhotosLocalDataSourceImp
import com.android.data.source.local.base.AppDatabase
import com.android.data.source.remote.base.ApiService
import com.android.data.source.remote.PhotosRemoteDataSource
import com.android.data.source.remote.PhotosRemoteDataSourceImp
import com.android.domain.repository.PhotosRepository
import com.android.domain.usecase.GetPhotosUseCase
import com.android.presentation.detail.DetailViewModel
import com.android.presentation.photos.PhotosViewModel
import org.koin.dsl.module

val appModule = module {

    //Repository & Data sources
    single { createPhotosRepository(get(), get()) }

    single { createPhotosRemoteDataSource(get()) }

    single { createPhotosLocalDataSource(get()) }

    //Photos
    single { PhotosViewModel(get()) }

    single { createGetPhotosUseCase(get()) }

    //Detail
    single { DetailViewModel() }

}


fun createPhotosRepository(
    photosRemoteDataSource: PhotosRemoteDataSource,
    photosLocalDataSource: PhotosLocalDataSource
): PhotosRepository {
    return PhotosRepositoryImp(photosRemoteDataSource, photosLocalDataSource)
}

fun createPhotosRemoteDataSource(apiService: ApiService): PhotosRemoteDataSource {
    return PhotosRemoteDataSourceImp(apiService)
}

fun createPhotosLocalDataSource(appDatabase: AppDatabase): PhotosLocalDataSource {
    return PhotosLocalDataSourceImp(appDatabase)
}

fun createGetPhotosUseCase(photosRepository: PhotosRepository): GetPhotosUseCase {
    return GetPhotosUseCase(photosRepository)
}