package com.example.proyek_mad.data

data class Module (
    val materi_id:Int,
    val kelas_id:Int,
    val judul_materi:String,
    val konten_materi:String,
    val urutan_materi_dalam_kelas:Int,
    val deskripsi_singkat: String
)