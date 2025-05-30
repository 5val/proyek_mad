package com.example.proyek_mad.data.room.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BasicResponse(
    val message: String
)