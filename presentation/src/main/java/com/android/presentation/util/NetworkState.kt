package com.android.presentation.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object NetworkState{

    fun isAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        //Internet connectivity check in Android Q
        val networks = connectivityManager.allNetworks
        var hasInternet = false
        if (networks.isNotEmpty()) {
            for (network in networks) {
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                if (networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) hasInternet = true
            }
        }
        return hasInternet
    }
}
