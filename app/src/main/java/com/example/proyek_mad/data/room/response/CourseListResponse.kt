package com.example.proyek_mad.data.room.response

import com.example.proyek_mad.data.room.receive.CourseJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseListResponse(
    val data: List<CourseJson>
)