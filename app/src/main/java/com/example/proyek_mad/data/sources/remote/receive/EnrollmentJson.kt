package com.example.proyek_mad.data.sources.remote.receive

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EnrollmentJson(
    val enrollment_id: Int,
    val user_id: Int,
    val kelas_id: Int,
    val tanggal_mulai: String,
    val tanggal_selesai: String?,
    val status_kelas: String,
    val materi_terakhir_diakses_id: Int?,
    val skor_akhir_kelas: Float?,
    val created_at: String,
    val updated_at: String
)