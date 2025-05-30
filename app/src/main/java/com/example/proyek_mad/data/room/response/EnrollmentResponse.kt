package com.example.proyek_mad.data.room.response

import com.example.proyek_mad.data.room.receive.EnrollmentJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EnrollmentResponse(
    val data: EnrollmentJson
)