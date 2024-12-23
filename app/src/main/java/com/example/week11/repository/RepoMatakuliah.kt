package com.example.week11.repository

import com.example.week11.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

interface RepoMatakuliah {
    suspend fun insertMatakuliah(matakuliah: Matakuliah)
    fun getAllMatakuliah(): Flow<List<Matakuliah>>
    fun getMatakuliah(kodeMk: String): Flow<Matakuliah>
    suspend fun deleteMatakuliah(matakuliah: Matakuliah)
    suspend fun updateMatakuliah(matakuliah: Matakuliah)
}