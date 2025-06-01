package com.example.proyek_mad.data.sources.remote.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequest (
    val username:String,
    val email:String,
    var password:String,
    var nama_lengkap:String,
)