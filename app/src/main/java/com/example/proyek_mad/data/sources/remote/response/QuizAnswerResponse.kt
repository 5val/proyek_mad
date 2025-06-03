package com.example.proyek_mad.data.sources.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuizAnswerResponse(
    val data: com.example.proyek_mad.data.sources.remote.receive.QuizAnswerJson
)