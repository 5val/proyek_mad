package com.example.proyek_mad.data.room.response

import com.example.proyek_mad.data.room.receive.ProgressSummaryJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProgressSummaryResponse(
    val data: ProgressSummaryJson
)