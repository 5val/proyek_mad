package com.example.proyek_mad.data.sources.remote.receive

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BestScoreJson(
    val user_id: Int,
    val kelas_id: Int,
    val nilai: Int
)