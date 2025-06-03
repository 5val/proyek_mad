package com.example.proyek_mad.data.sources.remote.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScoreQuizRequest(
    val nilai: Int,
    val status_lulus: Int
)