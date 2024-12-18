package com.example.week11.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.week11.data.entity.Dosen

@Dao
interface DosenDao {
    @Insert
    suspend fun insertDosen(dosen: Dosen)
}