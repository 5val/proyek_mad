package com.example.proyek_mad.data

import com.example.proyek_mad.data.sources.local.User.UserEntity

data class User (
    val user_id:Int,
    val username:String,
    val email:String,
    var password:String,
    var nama_lengkap:String,
){
    fun toUserEntity():UserEntity{
        return UserEntity(
            userId = this.user_id,
            username = this.username,
            email = this.email,
            password = this.password,
            namaLengkap = this.nama_lengkap

        )
    }
}