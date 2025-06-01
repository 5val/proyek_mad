package com.example.proyek_mad.data.sources.remote.receive

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserJson (
    val user_id:Int,
    val username:String,
    val email:String,
    var password:String,
    var nama_lengkap:String,
)