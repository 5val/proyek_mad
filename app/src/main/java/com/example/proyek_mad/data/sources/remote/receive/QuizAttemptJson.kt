package com.example.proyek_mad.data.sources.remote.receive

import com.example.proyek_mad.data.QuizAttempt
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuizAttemptJson(
    val attempt_id:Int,
    val user_id:Int,
    val kuis_id:Int,
    val enrollment_id:Int,
    var skor_diperoleh:Int?,
    var status_lulus:Int?,
    val created_at: String,
    val updated_at: String
){
    fun toQuizAttempt(): QuizAttempt {
        return QuizAttempt(
            attempt_id = this.attempt_id,
            user_id = this.user_id,
            kuis_id = this.kuis_id,
            enrollment_id = this.enrollment_id,
            skor_diperoleh = this.skor_diperoleh,
            status_lulus = this.status_lulus
        )
    }
}