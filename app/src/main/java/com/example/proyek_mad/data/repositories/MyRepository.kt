package com.example.proyek_mad.data.repositories

import com.example.proyek_mad.data.sources.remote.receive.CourseJson
import com.example.proyek_mad.data.sources.remote.receive.MaterialJson
import com.example.proyek_mad.data.sources.remote.receive.UserJson
import com.example.proyek_mad.data.sources.remote.request.EditPenggunaRequest
import com.example.proyek_mad.data.sources.remote.request.LoginRequest
import com.example.proyek_mad.data.sources.remote.request.RegisterRequest
import com.example.proyek_mad.data.sources.remote.response.BasicResponse

interface MyRepository {
    suspend fun login(request: LoginRequest): Result<UserJson>
    suspend fun register(request: RegisterRequest): Result<BasicResponse>
    suspend fun editPengguna(userId: Int, request: EditPenggunaRequest): Result<BasicResponse>

    // Course and Enrollment Operations
    suspend fun getAllPublishedCourses(): Result<List<CourseJson>>
    suspend fun getCourseById(courseId: Int): Result<CourseJson>
    suspend fun getOngoingCourse(userId: Int): Result<List<CourseJson>>
    suspend fun getCompletedCourse(userId: Int): Result<List<CourseJson>>
    suspend fun enrollUserToCourse(kelasId: Int, userId: Int): Result<BasicResponse>

    // Material Operations
    suspend fun getMaterialsByCourse(courseId: Int): Result<List<MaterialJson>>
    suspend fun getMaterialById(materialId: Int): Result<MaterialJson>
}