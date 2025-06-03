package com.example.proyek_mad.data.sources.remote.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateQuizAttemptRequest(
    val user_id: Int,
    val enrollment_id: Int
)