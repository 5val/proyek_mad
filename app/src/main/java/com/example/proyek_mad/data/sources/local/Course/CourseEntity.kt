package com.example.proyek_mad.data.sources.local.Course

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "kelas",
)
data class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kelas_id")
    val kelasId: Int = 0,

    @ColumnInfo(name = "nama_kelas")
    val namaKelas: String,

    @ColumnInfo(name = "deskripsi_kelas")
    val deskripsiKelas: String?,

    @ColumnInfo(name = "prasyarat_kelas_id")
    val prasyaratKelasId: Int?,

//    @ColumnInfo(name = "created_at")
//    val createdAt: Date = Date(),
//
//    @ColumnInfo(name = "updated_at")
//    val updatedAt: Date = Date()
)