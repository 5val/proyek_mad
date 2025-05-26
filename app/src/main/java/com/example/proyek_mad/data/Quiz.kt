package com.example.proyek_mad.data

data class Quiz (
    val kuis_id:Int,
    val kelas_id:Int,
    val judul_kuis:String,
    val batas_waktu_pengerjaan_menit:String,
    val nilai_minimum_lulus:Int
)