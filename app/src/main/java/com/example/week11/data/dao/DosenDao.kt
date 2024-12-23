package com.example.week11.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.week11.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface DosenDao {
    @Insert
    suspend fun insertDosen(dosen: Dosen)
    @Query("SELECT * FROM dosen ORDER BY nidn ASC")
    fun getAllDosen(): Flow<List<Dosen>>
    @Query("SELECT * FROM dosen WHERE nidn = :nidn")
    fun getDosen(nidn: String): Flow<Dosen>
}