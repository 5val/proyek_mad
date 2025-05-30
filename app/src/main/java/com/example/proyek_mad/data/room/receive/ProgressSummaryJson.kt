package com.example.proyek_mad.data.room.receive

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProgressSummaryJson(
    val user_id: Int,
    val total_enrolled_courses: Int,
    val completed_courses: Int,
    val in_progress_courses: Int,
    val total_materials_completed: Int,
    val average_score: Float?
)