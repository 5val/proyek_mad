package com.example.proyek_mad.data.room.receive

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MaterialCountJson(
    val count: Int
)