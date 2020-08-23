package com.allegorit.mvvmpoc.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface MuseumDao {
    @Query("SELECT * FROM  tb_museum")
    fun museum(): LiveData<List<MuseumDTO>>

    @Insert(onConflict = REPLACE)
    suspend fun add(museumDTO: List<MuseumDTO>)

    @Query("DELETE FROM tb_museum")
    suspend fun deleteAll()
}