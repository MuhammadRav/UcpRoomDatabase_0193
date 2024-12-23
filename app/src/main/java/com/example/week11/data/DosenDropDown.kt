package com.example.week11.data

import android.content.Context
import com.example.week11.data.database.KrsDatabase
import com.example.week11.data.entity.Dosen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object DosenDropDown {
    var option: List<Dosen> = listOf()
    fun loadData(context: Context) {
        val db = KrsDatabase.getDatabase(context)
        val dosenDao = db.dosenDao()

        runBlocking {
            option = dosenDao.getAllDosen().first()
        }
    }

}