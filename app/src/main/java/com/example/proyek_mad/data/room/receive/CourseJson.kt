package com.example.proyek_mad.data.room.receive

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseJson(
    val kelas_id: Int,
    val nama_kelas: String,
    val deskripsi_kelas: String?,
    val prasyarat_kelas_id: Int?,
    val thumbnail_url: String?,
    val status_publikasi: String,
    val created_at: String,
    val updated_at: String
)