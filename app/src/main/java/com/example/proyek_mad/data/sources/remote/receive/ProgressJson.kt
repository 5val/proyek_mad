package com.example.proyek_mad.data.sources.remote.receive

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProgressJson(
    val progress_id: Int,
    val enrollment_id: Int,
    val materi_id: Int,
    val status_selesai: Boolean,
    val tanggal_diselesaikan: String?,
    val created_at: String,
    val updated_at: String
)