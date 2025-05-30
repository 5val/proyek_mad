package com.example.proyek_mad.data.room.response

import com.example.proyek_mad.data.room.receive.ProgressCountJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProgressCountResponse(
    val data: ProgressCountJson
)