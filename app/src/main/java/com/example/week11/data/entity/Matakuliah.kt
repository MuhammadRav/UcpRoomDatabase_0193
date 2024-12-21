package com.example.week11.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matakuliah")
data class Matakuliah(
    @PrimaryKey
    val kodeMk: String,
    val namaMk: String,
    val sks: String,
    val semester: String,
    val jenisMk: String,
    val dosenPengampu: String,
    )
