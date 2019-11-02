package com.infosys.assignment.api

import com.infosys.assignment.application.MyApplication
import com.infosys.assignment.connection.NetworkConnection
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/*
* RetrofitBuilder class with build method for get instance of retrofit to make API call
* BASE_URL its an end point url
* cache is for store data for offline when network is not available
*
* */

class RetrofitBuilder {
    companion object {
        private const val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

        // For offline mode, cache
        private const val cacheSize = (5 * 1024 * 1024).toLong()
        private val cache = Cache(MyApplication.instance!!.cacheDir, cacheSize)

        // Return retrofit instance for API network call
        fun build(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .cache(cache)
                        .addInterceptor { chain ->
                            var request = chain.request()
                            request = if (NetworkConnection.hasNetwork(MyApplication.instance!!)) {
                                request.newBuilder()
                                    .header(
                                        "Cache-Control",
                                        "public, max-age=" + 5
                                    )
                                    .addHeader(
                                        "Accept",
                                        "Application/JSON"
                                    )
                                    .build()
                            } else {
                                request.newBuilder()
                                    .header(
                                        "Cache-Control",
                                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                                    )
                                    .addHeader(
                                        "Accept",
                                        "Application/JSON"
                                    )
                                    .build()
                            }
                            chain.proceed(request)
                        }
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}