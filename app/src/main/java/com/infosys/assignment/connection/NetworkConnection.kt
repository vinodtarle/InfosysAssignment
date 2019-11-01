package com.infosys.assignment.connection

import android.content.Context
import android.net.ConnectivityManager
import com.infosys.assignment.application.MyApplication

class NetworkConnection {

    companion object {
        fun isInternet(): Boolean {
            val connectivity = MyApplication.instance!!.applicationContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivity.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }
    }
}