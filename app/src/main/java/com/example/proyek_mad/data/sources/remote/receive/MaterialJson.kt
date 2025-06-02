package com.example.proyek_mad.data.sources.remote.receive

import com.example.proyek_mad.data.Module
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MaterialJson(
    val materi_id: Int,
    val kelas_id: Int,
    val judul_materi: String,
    val deskripsi_singkat: String,
    val konten_materi: String?,
    val urutan_materi_dalam_kelas: Int,
    val created_at: String,
    val updated_at: String
){
    fun toMaterial():Module{
        return Module(
            materi_id = this.materi_id,
            kelas_id = this.kelas_id,
            judul_materi = this.judul_materi,
            deskripsi_singkat = this.deskripsi_singkat,
            konten_materi = this.konten_materi?:"",
            urutan_materi_dalam_kelas = this.urutan_materi_dalam_kelas
        )
    }
}