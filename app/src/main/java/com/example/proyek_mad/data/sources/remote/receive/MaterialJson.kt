package com.example.proyek_mad.data.sources.remote.receive

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MaterialJson(
    val materi_id: Int,
    val kelas_id: Int,
    val judul_materi: String,
    val deskripsi_singkat: String,
    val konten_materi: String?,
    val tipe_materi: String,
    val urutan_materi_dalam_kelas: Int,
    val created_at: String,
    val updated_at: String
)