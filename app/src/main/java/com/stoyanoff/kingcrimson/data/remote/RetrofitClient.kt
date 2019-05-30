package com.stoyanoff.kingcrimson.data.remote

import com.google.gson.GsonBuilder
import com.stoyanoff.kingcrimson.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */

class RetrofitClient {

    private val retrofitBuilder by lazy { Retrofit.Builder() }
    private lateinit var httpClientBuilder: OkHttpClient.Builder

    fun addCallAdapterFactory(): RetrofitClient {
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        return this
    }

    fun addConverterFactory(): RetrofitClient {
        val gson = GsonBuilder().setLenient().create()

        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson))

        return this
    }

    fun addClient(): RetrofitClient {
        httpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }

        retrofitBuilder.client(httpClientBuilder.build())

        return this
    }

    fun baseUrl(baseUrl: String): RetrofitClient {
        retrofitBuilder.baseUrl(baseUrl)

        return this
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofitBuilder.build().create(serviceClass)
    }
}