package com.example.proyek_mad.data.room.receive

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProgressCountJson(
    val total_materials: Int,
    val completed_materials: Int
)