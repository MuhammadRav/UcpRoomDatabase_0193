package com.example.week11.repository

import com.example.week11.data.entity.Dosen
    import kotlinx.coroutines.flow.Flow

interface RepoDosen {
    suspend fun insertDosen(dosen: Dosen)
    fun getAllDosen(): Flow<List<Dosen>>
}