package com.example.proyek_mad.data.sources.remote.response

import com.example.proyek_mad.data.sources.remote.receive.MaterialJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MaterialResponse(
    val data: com.example.proyek_mad.data.sources.remote.receive.MaterialJson
)