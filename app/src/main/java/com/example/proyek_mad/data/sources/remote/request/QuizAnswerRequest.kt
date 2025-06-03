package com.example.proyek_mad.data.sources.remote.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuizAnswerRequest(
    val attempt_id: Int,
    val pilihan: Int,
    val benar: Int
)