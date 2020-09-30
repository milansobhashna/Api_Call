package com.da.myapplication

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiConfig {
    val BASE_URL = "http://ayurvedchikitsa.xperttech.in/"

    fun getAPIService(): API? {
        return getClient()!!.create(API::class.java)
    }

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
        return retrofit
    }
}