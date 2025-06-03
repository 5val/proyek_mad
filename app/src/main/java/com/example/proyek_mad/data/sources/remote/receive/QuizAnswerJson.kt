package com.example.proyek_mad.data.sources.remote.receive

import com.example.proyek_mad.data.Question
import com.example.proyek_mad.data.QuizAnswer
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuizAnswerJson(
    val user_jawaban_id:Int,
    val attempt_id:Int,
    val soal_id:Int,
    val pilihan_jawaban_id_dipilih:Int,
    val apakah_jawaban_benar:Int,
    val created_at: String,
    val updated_at: String
){
    fun toQuizAnswer(): QuizAnswer {
        return QuizAnswer(
            user_jawaban_id = this.user_jawaban_id,
            attempt_id = this.attempt_id,
            soal_id = this.soal_id,
            pilihan_jawaban_id_dipilih = this.pilihan_jawaban_id_dipilih,
            apakah_jawaban_benar = this.apakah_jawaban_benar
        )
    }
}