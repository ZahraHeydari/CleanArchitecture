package com.android.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel() {

    var isLoadingData = MutableLiveData<Boolean>()
        private set

    fun showLoadingProgressBar() {
        isLoadingData.value = true
    }

    fun hideLoadingProgressBar(){
        isLoadingData.value = false
    }

    override fun onCleared() {
        viewModelScope.cancel() //To avoid memory leak
        super.onCleared()

    }


}

