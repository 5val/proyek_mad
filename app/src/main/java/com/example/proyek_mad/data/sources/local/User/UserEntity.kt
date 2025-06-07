package com.example.proyek_mad.data.sources.local.User

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyek_mad.data.User
import java.util.Date

@Entity(tableName = "pengguna")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    val userId: Int = 0,

    @ColumnInfo(name = "username")
    val username: String?,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "nama_lengkap")
    val namaLengkap: String?,

//    @ColumnInfo(name = "tanggal_daftar")
//    val tanggalDaftar: Date = Date(),
//
//    @ColumnInfo(name = "terakhir_login")
//    val terakhirLogin: Date?,
//
//    @ColumnInfo(name = "created_at")
//    val createdAt: Date = Date(),
//
//    @ColumnInfo(name = "updated_at")
//    val updatedAt: Date = Date()
){
    fun toUser():User{
        return User(
        user_id = this.userId,
        username = this.username?:"",
        email = this.email,
        password = this.password,
        nama_lengkap = this.namaLengkap?:""
        )
    }
}