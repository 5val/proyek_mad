package com.example.proyek_mad.data.sources.remote.receive

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseWithProgressJson(
    val kelas_id: Int,
    val nama_kelas: String,
    val deskripsi_kelas: String?,
    val prasyarat_kelas_id: Int?,
    val thumbnail_url: String?,
    val status_publikasi: String,
    val total_materials: Int,
    val completed_materials: Int,
    val progress_percentage: Double,
    val enrollment_status: String?,
    val created_at: String,
    val updated_at: String
)