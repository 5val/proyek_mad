package com.example.proyek_mad.data.sources.remote

import com.example.proyek_mad.data.sources.remote.receive.CourseJson
import com.example.proyek_mad.data.sources.remote.receive.MaterialJson
import com.example.proyek_mad.data.sources.remote.receive.UserJson
import com.example.proyek_mad.data.sources.remote.request.EditPenggunaRequest
import com.example.proyek_mad.data.sources.remote.request.EnrollmentRequest
import com.example.proyek_mad.data.sources.remote.request.LoginRequest
import com.example.proyek_mad.data.sources.remote.request.RegisterRequest
import com.example.proyek_mad.data.sources.remote.response.BasicResponse
import retrofit2.Response

class RetrofitDataSource(private val apiService: WebService) : RemoteDataSource {

    // Helper function to safely execute API calls and wrap results in a Result object
    private suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                // Handle API errors (e.g., 400, 500 status codes)
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Result.failure(RuntimeException("API Error: ${response.code()} - $errorBody"))
            }
        } catch (e: Exception) {
            // Handle network errors (e.g., no internet connection, timeout)
            Result.failure(e)
        }
    }

    // User Operations
    override suspend fun login(request: LoginRequest): Result<UserJson> {
        return safeApiCall { apiService.login(request) }
    }

    override suspend fun register(request: RegisterRequest): Result<BasicResponse> {
        return safeApiCall { apiService.register(request) }
    }

    override suspend fun editPengguna(userId: Int, request: EditPenggunaRequest): Result<BasicResponse> {
        return safeApiCall { apiService.editPengguna(userId, request) }
    }

    // Course and Enrollment Operations
    override suspend fun getAllPublishedCourses(): Result<List<CourseJson>> {
        return safeApiCall { apiService.getAllPublishedCourses() }
    }

    override suspend fun getCourseById(courseId: Int): Result<CourseJson> {
        return safeApiCall { apiService.getCourseById(courseId) }
    }

    override suspend fun getOngoingCourse(userId: Int): Result<List<CourseJson>> {
        return safeApiCall { apiService.getOngoingCourse(userId) }
    }

    override suspend fun getCompletedCourse(userId: Int): Result<List<CourseJson>> {
        return safeApiCall { apiService.getCompletedCourse(userId) }
    }

    override suspend fun enrollUserToCourse(kelasId: Int, userId: Int): Result<BasicResponse> {
        // The API expects a JSON object for user_id, so we map it.
        return safeApiCall { apiService.enrollUserToCourse(EnrollmentRequest(userId, kelasId)) }
    }

    // Material Operations
    override suspend fun getMaterialsByCourse(courseId: Int): Result<List<MaterialJson>> {
        // The API expects a JSON object for courseId in the body, so we map it.
        return safeApiCall { apiService.getMaterialsByCourse(courseId) }
    }

    override suspend fun getMaterialById(materialId: Int): Result<MaterialJson> {
        return safeApiCall { apiService.getMaterialById(materialId) }
    }
}