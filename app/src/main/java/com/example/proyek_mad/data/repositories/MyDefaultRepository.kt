package com.example.proyek_mad.data.repositories

import com.example.proyek_mad.data.sources.local.LocalDataSource
import com.example.proyek_mad.data.sources.remote.RemoteDataSource
import com.example.proyek_mad.data.sources.remote.receive.CourseJson
import com.example.proyek_mad.data.sources.remote.receive.MaterialJson
import com.example.proyek_mad.data.sources.remote.receive.UserJson
import com.example.proyek_mad.data.sources.remote.request.EditPenggunaRequest
import com.example.proyek_mad.data.sources.remote.request.LoginRequest
import com.example.proyek_mad.data.sources.remote.request.RegisterRequest
import com.example.proyek_mad.data.sources.remote.response.BasicResponse

class MyDefaultRepository(
    localDataSource: LocalDataSource,
    remoteDataSource: RemoteDataSource
):MyRepository {
    override suspend fun login(request: LoginRequest): Result<UserJson> {
        TODO("Not yet implemented")
    }

    override suspend fun register(request: RegisterRequest): Result<BasicResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun editPengguna(
        userId: Int,
        request: EditPenggunaRequest
    ): Result<BasicResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPublishedCourses(): Result<List<CourseJson>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCourseById(courseId: Int): Result<CourseJson> {
        TODO("Not yet implemented")
    }

    override suspend fun getOngoingCourse(userId: Int): Result<List<CourseJson>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCompletedCourse(userId: Int): Result<List<CourseJson>> {
        TODO("Not yet implemented")
    }

    override suspend fun enrollUserToCourse(kelasId: Int, userId: Int): Result<BasicResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getMaterialsByCourse(courseId: Int): Result<List<MaterialJson>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMaterialById(materialId: Int): Result<MaterialJson> {
        TODO("Not yet implemented")
    }

}