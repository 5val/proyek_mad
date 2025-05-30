package com.example.proyek_mad.data.room.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateCourseRequest(
    val nama_kelas: String,
    val deskripsi_kelas: String?,
    val prasyarat_kelas_id: Int?,
    val thumbnail_url: String?,
    val status_publikasi: String = "draft"
)