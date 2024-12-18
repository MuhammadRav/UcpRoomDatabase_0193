package com.example.week11.repository

import com.example.week11.data.entity.Dosen

interface RepoDosen {
    suspend fun insertDosen(dosen: Dosen)

}