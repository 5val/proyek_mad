package com.example.proyek_mad.data.room.response

import com.example.proyek_mad.data.room.receive.MaterialCountJson
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MaterialCountResponse(
    val data: MaterialCountJson
)