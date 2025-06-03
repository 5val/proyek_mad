package com.example.proyek_mad.data.sources.remote.receive

import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.Quiz
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuizJson(
    val kuis_id:Int,
    val kelas_id:Int,
    val judul_kuis:String,
    val batas_waktu_pengerjaan_menit:String?,
    val nilai_minimum_lulus:Int,
    val created_at: String,
    val updated_at: String
){
    fun toQuiz(): Quiz {
        return Quiz(
            kuis_id = this.kuis_id,
            kelas_id = this.kelas_id,
            judul_kuis = this.judul_kuis,
            batas_waktu_pengerjaan_menit = this.batas_waktu_pengerjaan_menit?:"1",
            nilai_minimum_lulus = this.nilai_minimum_lulus
        )
    }
}