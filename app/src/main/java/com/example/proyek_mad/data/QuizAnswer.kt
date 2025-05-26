package com.example.proyek_mad.data

data class QuizAnswer (
    val user_jawaban_id:Int,
    val attempt_id:Int,
    val soal_id:Int,
    val pilihan_jawaban_id_dipilih:Int,
    val apakah_jawaban_benar:Int
)