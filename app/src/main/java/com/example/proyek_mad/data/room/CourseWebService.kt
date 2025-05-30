package com.example.proyek_mad.data.room

import com.example.proyek_mad.data.Dao.data_class.CourseWithProgress
import com.example.proyek_mad.data.room.receive.CourseJson
import com.example.proyek_mad.data.room.receive.EnrollmentJson
import com.example.proyek_mad.data.room.receive.MaterialCountJson
import com.example.proyek_mad.data.room.receive.MaterialJson
import com.example.proyek_mad.data.room.receive.ProgressCountJson
import com.example.proyek_mad.data.room.receive.ProgressJson
import com.example.proyek_mad.data.room.receive.ProgressSummaryJson
import com.example.proyek_mad.data.room.request.CreateCourseRequest
import com.example.proyek_mad.data.room.request.EnrollmentRequest
import com.example.proyek_mad.data.room.request.ProgressRequest
import com.example.proyek_mad.data.room.request.UpdateCourseRequest
import com.example.proyek_mad.data.room.request.UpdateEnrollmentRequest
import com.example.proyek_mad.data.room.request.UpdateLastAccessedRequest
import com.example.proyek_mad.data.room.request.UpdateMaterialProgressRequest
import com.example.proyek_mad.data.room.response.BasicResponse
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
    suspend fun getAllPublishedCourses(): Response<List<CourseJson>>

    @GET("courses/{courseId}")
    suspend fun getCourseById(@Path("courseId") courseId: Int): Response<CourseJson>

    @POST("courses")
    suspend fun insertCourse(@Body request: CreateCourseRequest): Response<CourseJson>

    @PUT("courses/{courseId}")
    suspend fun updateCourse(
        @Path("courseId") courseId: Int,
        @Body request: UpdateCourseRequest
    ): Response<CourseJson>

    @DELETE("courses/{courseId}")
    suspend fun deleteCourse(@Path("courseId") courseId: Int): Response<BasicResponse>

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
    ): Response<List<CourseJson>>

    // ===== MATERIALS QUERIES =====

    @GET("courses/{courseId}/materials")
    suspend fun getMaterialsByCourse(@Path("courseId") courseId: Int): Response<List<MaterialJson>>

    @GET("materials/{materialId}")
    suspend fun getMaterialById(@Path("materialId") materialId: Int): Response<MaterialJson>

    // ===== ENROLLMENT OPERATIONS =====

    @POST("enrollments")
    suspend fun enrollUserToCourse(@Body request: EnrollmentRequest): Response<EnrollmentJson>

    @GET("users/{userId}/courses/{courseId}/enrollment")
    suspend fun getUserEnrollment(
        @Path("userId") userId: Int,
        @Path("courseId") courseId: Int
    ): Response<EnrollmentJson>

    @GET("users/{userId}/enrollments")
    suspend fun getUserEnrollments(@Path("userId") userId: Int): Response<List<EnrollmentJson>>

    @PUT("enrollments/{enrollmentId}")
    suspend fun updateEnrollment(
        @Path("enrollmentId") enrollmentId: Int,
        @Body request: UpdateEnrollmentRequest
    ): Response<EnrollmentJson>

    // ===== PROGRESS OPERATIONS =====

    @POST("progress")
    suspend fun insertOrUpdateProgress(@Body request: ProgressRequest): Response<ProgressJson>

    @GET("enrollments/{enrollmentId}/progress")
    suspend fun getProgressByEnrollment(@Path("enrollmentId") enrollmentId: Int): Response<List<ProgressJson>>

    @GET("users/{userId}/courses/{courseId}/progress/details")
    suspend fun getUserCourseProgress(
        @Path("userId") userId: Int,
        @Path("courseId") courseId: Int
    ): Response<List<ProgressJson>>

    @PUT("enrollments/{enrollmentId}/materials/{materialId}/progress")
    suspend fun updateMaterialProgress(
        @Path("enrollmentId") enrollmentId: Int,
        @Path("materialId") materialId: Int,
        @Body request: UpdateMaterialProgressRequest
    ): Response<BasicResponse>

    // ===== PROGRESS SUMMARY =====

    @GET("users/{userId}/progress/summary")
    suspend fun getUserProgressSummary(@Path("userId") userId: Int): Response<ProgressSummaryJson>

    @GET("users/{userId}/courses/{courseId}/progress/count")
    suspend fun getCourseProgressCount(
        @Path("userId") userId: Int,
        @Path("courseId") courseId: Int
    ): Response<ProgressCountJson>

    // ===== UTILITY METHODS =====

    @PUT("enrollments/{enrollmentId}/last-accessed")
    suspend fun updateLastAccessedMaterial(
        @Path("enrollmentId") enrollmentId: Int,
        @Body request: UpdateLastAccessedRequest
    ): Response<BasicResponse>

    @GET("users/{userId}/courses/{courseId}/materials/completed/count")
    suspend fun getCompletedMaterialsCount(
        @Path("userId") userId: Int,
        @Path("courseId") courseId: Int
    ): Response<MaterialCountJson>

    @GET("courses/{courseId}/materials/count")
    suspend fun getTotalMaterialsCount(@Path("courseId") courseId: Int): Response<MaterialCountJson>
}