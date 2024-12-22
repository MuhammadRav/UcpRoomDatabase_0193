package com.example.week11.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.week11.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MatakuliahDao {
    @Insert
    suspend fun insertMatakuliah(matakuliah: Matakuliah)

    @Delete
    suspend fun deleteMatakuliah(matakuliah: Matakuliah)

    @Update
    suspend fun updateMatakuliah(matakuliah: Matakuliah)

    @Query("SELECT * FROM dosen ORDER BY nama ASC")
    fun getAllMatakuliah(): Flow<List<Matakuliah>>

    @Query("SELECT * FROM matakuliah WHERE kodeMk = :kodeMk")
    fun getMatakuliah(kodeMk: String): Flow<Matakuliah>
}