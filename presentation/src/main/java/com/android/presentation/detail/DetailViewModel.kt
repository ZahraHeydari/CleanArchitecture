package com.android.presentation.detail

import androidx.lifecycle.viewModelScope
import com.android.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailViewModel : BaseViewModel() {


    fun hideProgressBarWithDelay() {
        viewModelScope.launch {
            delay(500)
            hideLoadingProgressBar()
        }
    }
}