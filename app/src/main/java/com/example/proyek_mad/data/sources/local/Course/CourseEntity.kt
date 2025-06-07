package com.example.proyek_mad.data.sources.local.Course

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyek_mad.data.Course
import java.util.Date

@Entity(
    tableName = "kelas",
    primaryKeys = ["kelas_id", "user_id"]
)
data class CourseEntity(
    @ColumnInfo(name = "kelas_id")
    val kelasId: Int = 0,

    @ColumnInfo(name = "user_id")
    val userId: Int = 0,

    @ColumnInfo(name = "nama_kelas")
    val namaKelas: String,

    @ColumnInfo(name = "deskripsi_kelas")
    val deskripsiKelas: String?,

    @ColumnInfo(name = "prasyarat_kelas_id")
    val prasyaratKelasId: Int?,

    @ColumnInfo(name = "progress")
    val progress: Int?,
//    @ColumnInfo(name = "created_at")
//    val createdAt: Date = Date(),
//
//    @ColumnInfo(name = "updated_at")
//    val updatedAt: Date = Date()
){
    fun toCourse(): Course {
        return Course(
            kelas_id = this.kelasId,
            nama_kelas = this.namaKelas,
            deskripsi_kelas = this.deskripsiKelas,
            prasyarat_kelas_id = this.prasyaratKelasId,
            progress = progress?:0,
            attended = "sedang_dipelajari"
        )
    }
}