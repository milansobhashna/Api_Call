package com.da.myapplication

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class networkConnection(val mainActivity: MainActivity) {
    //region public methods
    fun isConnected(): Boolean {
        val info: NetworkInfo = this.getNetworkInfo()!!
        return info != null && info.isAvailable && info.isConnected
    }

    //endregion
    //region private internal methods
    private fun getNetworkInfo(): NetworkInfo? {
        val connectivityManager =
            mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo
    }
}