package com.example.proyek_mad.data.sources.remote.response

import com.example.proyek_mad.data.sources.remote.receive.MaterialCountJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MaterialCountResponse(
    val data: com.example.proyek_mad.data.sources.remote.receive.MaterialCountJson
)