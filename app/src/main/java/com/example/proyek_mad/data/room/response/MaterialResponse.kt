package com.example.proyek_mad.data.room.response

import com.example.proyek_mad.data.room.receive.MaterialJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MaterialResponse(
    val data: MaterialJson
)