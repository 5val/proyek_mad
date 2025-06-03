package com.example.proyek_mad.data.sources.remote.receive

import com.example.proyek_mad.data.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserJson (
    val user_id:Int,
    val username:String,
    val email:String,
    var password:String,
    var nama_lengkap:String,
){
    fun toUser():User{
        return User(
            user_id = this.user_id,
            username = this.username,
            email = this.email,
            password = this.password,
            nama_lengkap = this.nama_lengkap
        )
    }
}