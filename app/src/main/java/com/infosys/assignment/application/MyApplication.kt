package com.infosys.assignment.application

import android.app.Application

/*
* MyApplication class is for get application context for network checks.
* */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @get:Synchronized
        var instance: MyApplication? = null
            private set
    }
}
