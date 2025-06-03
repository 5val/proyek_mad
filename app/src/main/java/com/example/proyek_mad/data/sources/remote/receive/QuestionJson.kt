package com.example.proyek_mad.data.sources.remote.receive

import com.example.proyek_mad.data.Question
import com.example.proyek_mad.data.Quiz
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuestionJson(
    val soal_id:Int,
    val kuis_id:Int,
    val teks_soal:String,
    val urutan_soal:Int,
    val poin_soal:Int,
    val created_at: String,
    val updated_at: String
){
    fun toQuestion(): Question {
        return Question(
            soal_id = this.soal_id,
            kuis_id = this.kuis_id,
            teks_soal = this.teks_soal,
            urutan_soal = this.urutan_soal,
            poin_soal = this.poin_soal
        )
    }
}