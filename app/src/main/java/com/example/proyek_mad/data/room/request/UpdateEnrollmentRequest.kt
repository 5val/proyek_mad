package com.example.proyek_mad.data.room.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateEnrollmentRequest(
    val status_kelas: String?,
    val tanggal_selesai: String?,
    val materi_terakhir_diakses_id: Int?,
    val skor_akhir_kelas: Float?
)