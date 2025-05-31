package com.example.proyek_mad.data.sources.remote

import com.example.proyek_mad.data.sources.local.data_class.CourseWithProgress
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CourseWebService {

    // ===== BASIC CRUD OPERATIONS =====

    @GET("courses/published")
    suspend fun getAllPublishedCourses(): Response<List<com.example.proyek_mad.data.sources.remote.receive.CourseJson>>

    @GET("courses/{courseId}")
    suspend fun getCourseById(@Path("courseId") courseId: Int): Response<com.example.proyek_mad.data.sources.remote.receive.CourseJson>

    @POST("courses")
    suspend fun insertCourse(@Body request: com.example.proyek_mad.data.sources.remote.request.CreateCourseRequest): Response<com.example.proyek_mad.data.sources.remote.receive.CourseJson>

    @PUT("courses/{courseId}")
    suspend fun updateCourse(
        @Path("courseId") courseId: Int,
        @Body request: com.example.proyek_mad.data.sources.remote.request.UpdateCourseRequest
    ): Response<com.example.proyek_mad.data.sources.remote.receive.CourseJson>

    @DELETE("courses/{courseId}")
    suspend fun deleteCourse(@Path("courseId") courseId: Int): Response<com.example.proyek_mad.data.sources.remote.response.BasicResponse>

    // ===== COURSE WITH PROGRESS QUERIES =====

    @GET("users/{userId}/courses/progress")
    suspend fun getCoursesWithProgress(@Path("userId") userId: Int): Response<List<CourseWithProgress>>

    @GET("users/{userId}/courses/{courseId}/progress")
    suspend fun getCourseWithProgress(
        @Path("userId") userId: Int,
        @Path("courseId") courseId: Int
    ): Response<CourseWithProgress>

    @GET("users/{userId}/courses/status/{status}")
    suspend fun getUserCoursesByStatus(
        @Path("userId") userId: Int,
        @Path("status") status: String
    ): Response<List<com.example.proyek_mad.data.sources.remote.receive.CourseJson>>

    // ===== MATERIALS QUERIES =====

    @GET("courses/{courseId}/materials")
    suspend fun getMaterialsByCourse(@Path("courseId") courseId: Int): Response<List<com.example.proyek_mad.data.sources.remote.receive.MaterialJson>>

    @GET("materials/{materialId}")
    suspend fun getMaterialById(@Path("materialId") materialId: Int): Response<com.example.proyek_mad.data.sources.remote.receive.MaterialJson>

    // ===== ENROLLMENT OPERATIONS =====

    @POST("enrollments")
    suspend fun enrollUserToCourse(@Body request: com.example.proyek_mad.data.sources.remote.request.EnrollmentRequest): Response<com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson>

    @GET("users/{userId}/courses/{courseId}/enrollment")
    suspend fun getUserEnrollment(
        @Path("userId") userId: Int,
        @Path("courseId") courseId: Int
    ): Response<com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson>

    @GET("users/{userId}/enrollments")
    suspend fun getUserEnrollments(@Path("userId") userId: Int): Response<List<com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson>>

    @PUT("enrollments/{enrollmentId}")
    suspend fun updateEnrollment(
        @Path("enrollmentId") enrollmentId: Int,
        @Body request: com.example.proyek_mad.data.sources.remote.request.UpdateEnrollmentRequest
    ): Response<com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson>

    // ===== PROGRESS OPERATIONS =====

    @POST("progress")
    suspend fun insertOrUpdateProgress(@Body request: com.example.proyek_mad.data.sources.remote.request.ProgressRequest): Response<com.example.proyek_mad.data.sources.remote.receive.ProgressJson>

    @GET("enrollments/{enrollmentId}/progress")
    suspend fun getProgressByEnrollment(@Path("enrollmentId") enrollmentId: Int): Response<List<com.example.proyek_mad.data.sources.remote.receive.ProgressJson>>

    @GET("users/{userId}/courses/{courseId}/progress/details")
    suspend fun getUserCourseProgress(
        @Path("userId") userId: Int,
        @Path("courseId") courseId: Int
    ): Response<List<com.example.proyek_mad.data.sources.remote.receive.ProgressJson>>

    @PUT("enrollments/{enrollmentId}/materials/{materialId}/progress")
    suspend fun updateMaterialProgress(
        @Path("enrollmentId") enrollmentId: Int,
        @Path("materialId") materialId: Int,
        @Body request: com.example.proyek_mad.data.sources.remote.request.UpdateMaterialProgressRequest
    ): Response<com.example.proyek_mad.data.sources.remote.response.BasicResponse>

    // ===== PROGRESS SUMMARY =====

    @GET("users/{userId}/progress/summary")
    suspend fun getUserProgressSummary(@Path("userId") userId: Int): Response<com.example.proyek_mad.data.sources.remote.receive.ProgressSummaryJson>

    @GET("users/{userId}/courses/{courseId}/progress/count")
    suspend fun getCourseProgressCount(
        @Path("userId") userId: Int,
        @Path("courseId") courseId: Int
    ): Response<com.example.proyek_mad.data.sources.remote.receive.ProgressCountJson>

    // ===== UTILITY METHODS =====

    @PUT("enrollments/{enrollmentId}/last-accessed")
    suspend fun updateLastAccessedMaterial(
        @Path("enrollmentId") enrollmentId: Int,
        @Body request: com.example.proyek_mad.data.sources.remote.request.UpdateLastAccessedRequest
    ): Response<com.example.proyek_mad.data.sources.remote.response.BasicResponse>

    @GET("users/{userId}/courses/{courseId}/materials/completed/count")
    suspend fun getCompletedMaterialsCount(
        @Path("userId") userId: Int,
        @Path("courseId") courseId: Int
    ): Response<com.example.proyek_mad.data.sources.remote.receive.MaterialCountJson>

    @GET("courses/{courseId}/materials/count")
    suspend fun getTotalMaterialsCount(@Path("courseId") courseId: Int): Response<com.example.proyek_mad.data.sources.remote.receive.MaterialCountJson>
}