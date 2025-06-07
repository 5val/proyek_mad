package com.example.proyek_mad.data.sources.local.User

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyek_mad.data.sources.local.Module.ModuleEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM pengguna WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Update
    suspend fun updateUser(userEntity: UserEntity)
}