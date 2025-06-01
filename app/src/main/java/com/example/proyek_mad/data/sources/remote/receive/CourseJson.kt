package com.example.proyek_mad.data.sources.remote.receive

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseJson(
    val kelas_id: Int,
    val nama_kelas: String,
    val deskripsi_kelas: String?,
    val prasyarat_kelas_id: Int?,
    val created_at: String,
    val updated_at: String
)