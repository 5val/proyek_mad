package com.example.proyek_mad.data.sources.remote.response

import com.example.proyek_mad.data.sources.remote.receive.CourseWithProgressJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseWithProgressListResponse(
    val data: List<com.example.proyek_mad.data.sources.remote.receive.CourseWithProgressJson>
)