package com.example.week11.repository

import com.example.week11.data.dao.MatakuliahDao
import com.example.week11.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

class LocalRepoMatakuliah(
    private val matakuliahDao: MatakuliahDao
): RepoMatakuliah {
    override suspend fun insertMatakuliah(matakuliah: Matakuliah) {
        matakuliahDao.insertMatakuliah(matakuliah)
    }
    override fun getAllMatakuliah(): Flow<List<Matakuliah>> {
        return matakuliahDao.getAllMatakuliah()
    }
}