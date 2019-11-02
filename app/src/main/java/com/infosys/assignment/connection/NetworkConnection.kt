package com.infosys.assignment.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/*
* NetworkConnection class with hasNetwork method for to check network/internet available
* Call this method by class name
* */
class NetworkConnection {

    companion object {
        // return true if network/internet is available otherwise false
        fun hasNetwork(context: Context): Boolean {
            var isConnected = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true
            return isConnected
        }
    }
}