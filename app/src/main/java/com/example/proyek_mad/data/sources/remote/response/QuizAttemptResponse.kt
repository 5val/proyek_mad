package com.example.proyek_mad.data.sources.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuizAttemptResponse(
    val data: com.example.proyek_mad.data.sources.remote.receive.QuizAttemptJson
)