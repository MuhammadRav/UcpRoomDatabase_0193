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
    override fun getMatakuliah(kodeMk: String): Flow<Matakuliah>{
        return  matakuliahDao.getMatakuliah(kodeMk)
    }
    override fun getAllMatakuliah(): Flow<List<Matakuliah>> {
        return matakuliahDao.getAllMatakuliah()
    }
    override suspend fun deleteMatakuliah(matakuliah: Matakuliah){
        matakuliahDao.deleteMatakuliah(matakuliah)
    }

    override suspend fun updateMatakuliah(matakuliah: Matakuliah){
        matakuliahDao.updateMatakuliah(matakuliah)
    }

}