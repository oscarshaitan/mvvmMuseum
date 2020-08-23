package com.allegorit.mvvmpoc.data.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("museums")
    suspend fun getMuseums(): Response<MuseumResponse>
}