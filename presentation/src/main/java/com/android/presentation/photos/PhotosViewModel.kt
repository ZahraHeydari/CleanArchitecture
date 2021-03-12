package com.android.presentation.photos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.domain.base.UseCaseResponse
import com.android.domain.model.PhotoEntity
import com.android.domain.usecase.GetPhotosUseCase
import com.android.presentation.base.BaseViewModel
import com.android.presentation.model.Photo
import com.android.presentation.model.toUI

class PhotosViewModel(private val getPhotosUseCase: GetPhotosUseCase) : BaseViewModel() {

    var photosData = MutableLiveData<List<Photo>>()
    private set

    fun getPhotos(isNetworkAvailable : Boolean){
        showLoadingProgressBar()
        getPhotosUseCase.invoke(viewModelScope, isNetworkAvailable, object : UseCaseResponse<List<PhotoEntity>?> {
            override fun onSuccess(result: List<PhotoEntity>?) {
                Log.d(TAG, "onSuccess() called with: result = ${result?.size}")
                hideLoadingProgressBar()
                photosData.value = result?.map {
                    it.toUI()
                }
            }

            override fun onError(throwable: Throwable) {
                Log.d(TAG, "onError() called with: error = ${throwable.message}")
                hideLoadingProgressBar()
            }
        })
    }

    companion object{
        private const val TAG = "PhotosViewModel"
    }
}