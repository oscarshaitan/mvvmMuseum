package com.allegorit.mvvmpoc.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    var client: OkHttpClient = OkHttpClient.Builder().build()
    private var retrofit =
        Retrofit.Builder().baseUrl("https://obscure-earth-55790.herokuapp.com/api/").addConverterFactory(GsonConverterFactory.create()).client(
            client
        ).build()

    fun <T> createRetrofitService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}