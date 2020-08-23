package com.allegorit.mvvmpoc.data.db

import androidx.lifecycle.LiveData

interface DbDataSource {

    fun museum(): LiveData<List<MuseumDTO>>
    suspend fun addMuseums(museums: List<MuseumDTO>)
    suspend fun deleteMuseums()
}