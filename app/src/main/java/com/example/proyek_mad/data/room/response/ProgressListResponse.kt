package com.example.proyek_mad.data.room.response

import com.example.proyek_mad.data.room.receive.ProgressJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProgressListResponse(
    val data: List<ProgressJson>
)