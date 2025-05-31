package com.example.proyek_mad.data.sources.remote.response

import com.example.proyek_mad.data.sources.remote.receive.ProgressCountJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProgressCountResponse(
    val data: com.example.proyek_mad.data.sources.remote.receive.ProgressCountJson
)