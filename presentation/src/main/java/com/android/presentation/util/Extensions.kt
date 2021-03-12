package com.android.presentation.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun View.snackBar(message: String, actionMessage: String, action : () -> Unit)
= Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
.setAction(actionMessage) {
    action()
}.show()