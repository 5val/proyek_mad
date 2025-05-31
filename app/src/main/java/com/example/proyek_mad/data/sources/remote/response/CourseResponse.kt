package com.example.proyek_mad.data.sources.remote.response

import com.example.proyek_mad.data.sources.remote.receive.CourseJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseResponse(
    val data: com.example.proyek_mad.data.sources.remote.receive.CourseJson
)