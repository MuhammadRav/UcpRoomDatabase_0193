package com.example.week11.repository

import com.example.week11.data.dao.DosenDao
import com.example.week11.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepoDosen(
    private val dosenDao: DosenDao
): RepoDosen {
    override suspend fun insertDosen(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }
    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }
}