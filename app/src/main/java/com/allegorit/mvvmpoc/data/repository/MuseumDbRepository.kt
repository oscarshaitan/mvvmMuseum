package com.allegorit.mvvmpoc.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.allegorit.mvvmpoc.data.db.DbDataSource
import com.allegorit.mvvmpoc.data.db.MuseumDTO
import com.allegorit.mvvmpoc.data.model.Museum

class MuseumDbRepository(private val dataSource: DbDataSource):DbRepository {
    override fun getMuseums(): LiveData<List<Museum>> {
        return Transformations.map(dataSource.museum()) {
            it.map { item ->
                Museum(item.id, item.name, item.photo)
            }
        }
    }

    override suspend fun sync(museums: List<Museum>) {
        dataSource.run {
            deleteMuseums()
            addMuseums(museums.map {
                MuseumDTO(it.id, it.name, it.photo)
            })
        }

    }
}

interface DbRepository {
    fun getMuseums(): LiveData<List<Museum>>
    suspend fun sync(museums: List<Museum>)
}