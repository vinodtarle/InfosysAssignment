package com.infosys.assignment.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {
        private const val API_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

        fun build(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(API_URL)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            chain.proceed(
                                chain.request()
                                    .newBuilder()
                                    .addHeader("Accept", "Application/JSON")
                                    .build()
                            )
                        }.build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}