package com.example.proyek_mad.data.sources.remote.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeminiRequest (
    val message:String
)