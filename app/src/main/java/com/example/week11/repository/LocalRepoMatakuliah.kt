package com.example.week11.repository

import com.example.week11.data.dao.MatakuliahDao
import com.example.week11.data.entity.Matakuliah

class LocalRepoMatakuliah(
    private val matakuliahDao: MatakuliahDao
): RepoMatakuliah {
    override suspend fun insertMatakuliah(matakuliah: Matakuliah) {
        matakuliahDao.insertMatakuliah(matakuliah)
    }
}