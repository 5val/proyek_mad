package com.example.proyek_mad.data.sources.remote.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EnrollmentRequest(
    val user_id: Int,
    val kelas_id: Int
)