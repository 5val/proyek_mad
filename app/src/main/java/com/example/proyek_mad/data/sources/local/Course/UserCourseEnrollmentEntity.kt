package com.example.proyek_mad.data.sources.local.Course

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "user_kelas_enrollment"
)
data class UserCourseEnrollmentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "enrollment_id")
    val enrollmentId: Int = 0,

    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "kelas_id")
    val kelasId: Int,

//    @ColumnInfo(name = "tanggal_mulai")
//    val tanggalMulai: Date = Date(),
//
//    @ColumnInfo(name = "tanggal_selesai")
//    val tanggalSelesai: Date?,

    @ColumnInfo(name = "status_kelas")
    val statusKelas: String = "terdaftar", // terdaftar, sedang_dipelajari, selesai, gagal

    @ColumnInfo(name = "materi_terakhir_diakses_id")
    val materiTerakhirDiaksesId: Int?,

    @ColumnInfo(name = "skor_akhir_kelas")
    val skorAkhirKelas: Float?,

)