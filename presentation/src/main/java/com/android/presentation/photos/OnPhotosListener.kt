package com.android.presentation.photos

import com.android.presentation.model.Photo

/**
 * To make an interaction between [PhotosFragment]
 * and [PhotosAdapter]
 * */
interface OnPhotosListener {

    fun onItemClick(photo: Photo)
}