package com.allegorit.mvvmpoc.data.db

import androidx.lifecycle.LiveData

class MuseumDbDataSource(db: MuseumDataBase?) : DbDataSource {
    private lateinit var museumDao: MuseumDao

    init {
        db?.let {
            museumDao = db.museumDao()
        }
    }

    override fun museum(): LiveData<List<MuseumDTO>> = museumDao.museum()

    override suspend fun addMuseums(museums: List<MuseumDTO>) = museumDao.add(museums)

    override suspend fun deleteMuseums() = museumDao.deleteAll()

}