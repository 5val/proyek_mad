package com.example.proyek_mad.data.sources.remote.response

import com.example.proyek_mad.data.sources.remote.receive.ProgressSummaryJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProgressSummaryResponse(
    val data: com.example.proyek_mad.data.sources.remote.receive.ProgressSummaryJson
)