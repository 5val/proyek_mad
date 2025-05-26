package com.example.proyek_mad.data

data class QuizAttempt (
    val attempt_id:Int,
    val user_Id:Int,
    val kuis_id:Int,
    val enrollment_id:Int,
    var skor_diperoleh:Int,
    var status_lulus:Int
)