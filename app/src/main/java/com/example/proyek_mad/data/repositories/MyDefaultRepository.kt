package com.example.proyek_mad.data.repositories

import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.data.sources.remote.RemoteDataSource
import com.example.proyek_mad.data.sources.remote.receive.UserJson
import com.example.proyek_mad.data.sources.remote.request.EditPenggunaRequest
import com.example.proyek_mad.data.sources.remote.request.LoginRequest
import com.example.proyek_mad.data.sources.remote.request.RegisterRequest
import com.example.proyek_mad.data.sources.remote.response.BasicResponse

class MyDefaultRepository(
//    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
):MyRepository {
    override suspend fun login(request: LoginRequest): Result<UserJson> {
        return remoteDataSource.login(request)
    }

    override suspend fun register(request: RegisterRequest): Result<BasicResponse> {
        return remoteDataSource.register(request)
    }

    override suspend fun editPengguna(
        userId: Int,
        request: EditPenggunaRequest
    ): Result<BasicResponse> {
        return remoteDataSource.editPengguna(userId, request)
    }

    override suspend fun getAllPublishedCourses(): Result<List<Course>> {
        return remoteDataSource.getAllPublishedCourses().map { list ->
            list.map { it.toCourse() }
        }
    }

    override suspend fun getCourseById(courseId: Int): Result<Course> {
        return remoteDataSource.getCourseById(courseId).map { it.toCourse() }
    }

    override suspend fun getOngoingCourse(userId: Int): Result<List<Course>> {
        return remoteDataSource.getOngoingCourse(userId).map { list->
            list.map { it.toCourse() }
        }
    }

    override suspend fun getCompletedCourse(userId: Int): Result<List<Course>> {
        return remoteDataSource.getCompletedCourse(userId).map { list->
            list.map { it.toCourse() }
        }
    }

    override suspend fun enrollUserToCourse(kelasId: Int, userId: Int): Result<BasicResponse> {
        return remoteDataSource.enrollUserToCourse(kelasId, userId)
    }

    override suspend fun getMaterialsByCourse(courseId: Int): Result<List<Module>> {
        return remoteDataSource.getMaterialsByCourse(courseId).map { list->
            list.map { it.toMaterial() }
        }
    }

    override suspend fun getMaterialById(materialId: Int): Result<Module> {
        return remoteDataSource.getMaterialById(materialId).map {
            it.toMaterial()
        }
    }

}