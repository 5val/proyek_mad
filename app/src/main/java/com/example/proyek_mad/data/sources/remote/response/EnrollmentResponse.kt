package com.example.proyek_mad.data.sources.remote.response

import com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EnrollmentResponse(
    val data: com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson
)