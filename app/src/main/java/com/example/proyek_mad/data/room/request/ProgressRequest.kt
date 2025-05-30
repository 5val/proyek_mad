package com.example.proyek_mad.data.room.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProgressRequest(
    val enrollment_id: Int,
    val materi_id: Int,
    val status_selesai: Boolean = false,
    val tanggal_diselesaikan: String?
)