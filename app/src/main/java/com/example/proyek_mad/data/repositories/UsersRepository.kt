package com.example.proyek_mad.data.repositories

import com.example.proyek_mad.data.User


interface UsersRepository {
    suspend fun register(user: User)
    suspend fun login()
    suspend fun getUserById(id:Int):User
    suspend fun editUser()
}