package com.android.presentation.main

import com.android.presentation.model.Photo

/**
 * To make an interaction between [MainActivity] & its child
 * */
interface OnMainActivityCallback {

    fun navigateToDetail(photo : Photo)
}