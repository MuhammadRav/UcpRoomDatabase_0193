package com.example.week11.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.week11.data.entity.Matakuliah

@Dao
interface MatakuliahDao {
    @Insert
    suspend fun insertMatakuliah(matakuliah: Matakuliah)

    @Delete
    suspend fun deleteMatakuliah(matakuliah: Matakuliah)

    @Update
    suspend fun updateMatakuliah(matakuliah: Matakuliah)

}