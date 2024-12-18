package com.example.week11.repository

import com.example.week11.data.entity.Matakuliah

interface RepoMatakuliah {
    suspend fun insertMatakuliah(matakuliah: Matakuliah)

}