package com.example.proyek_mad.data.room.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateLastAccessedRequest(
    val material_id: Int
)