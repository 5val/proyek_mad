package com.example.proyek_mad.data.sources.local

import com.example.proyek_mad.data.User
import com.example.proyek_mad.data.sources.local.Course.CourseEntity
import com.example.proyek_mad.data.sources.local.Module.ModuleEntity
import com.example.proyek_mad.data.sources.local.User.UserEntity

interface LocalDataSource {
    // Course methods
    suspend fun getCoursesByUserId(userId: Int): List<CourseEntity>
    suspend fun getCourseById(courseId: Int, userId: Int): CourseEntity?
    suspend fun insertCourse(courseEntity: CourseEntity): CourseEntity
    suspend fun deleteCourse(courseEntity: CourseEntity): CourseEntity

    // Module methods
    suspend fun getModulesByCourseId(courseId: Int): List<ModuleEntity>
    suspend fun getModule(moduleId:Int):ModuleEntity?
    suspend fun insertModule(moduleEntity: ModuleEntity): ModuleEntity
    suspend fun deleteModule(moduleEntity: ModuleEntity): ModuleEntity

    // User methods
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity?
    suspend fun insertUser(userEntity: UserEntity): UserEntity
    suspend fun updateUser(userEntity: UserEntity): UserEntity
}