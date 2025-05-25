package com.example.proyek_mad.data

data class Course (
    val kelas_id:Int,
    val nama_kelas:String,
    val deskripsi_kelas:String,
    val prasyarat_kelas_id:Int,
)