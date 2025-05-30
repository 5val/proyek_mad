package com.example.proyek_mad.data.room.response

import com.example.proyek_mad.data.room.receive.CourseWithProgressJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseWithProgressListResponse(
    val data: List<CourseWithProgressJson>
)