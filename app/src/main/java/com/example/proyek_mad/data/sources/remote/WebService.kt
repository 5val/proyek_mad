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
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    // user
    @POST("pengguna")
    suspend fun login(@Body loginRequest: LoginRequest):Response<UserJson>
    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest):Response<BasicResponse>
    @PUT("editPengguna/{userId}")
    suspend fun editPengguna(
        @Path("userId") userId:Int,
        @Body() editPenggunaRequest: EditPenggunaRequest): Response<BasicResponse>

    // kelas dan enroll
    @GET("kelas")
    suspend fun getAllPublishedCourses(): Response<List<CourseJson>>
    @GET("kelas/{courseId}")
    suspend fun getCourseById(@Path("courseId") courseId: Int): Response<CourseJson>
    @GET("ongoing/{userId}")
    suspend fun getOngoingCourse(@Path("userId") userId: Int): Response<List<CourseJson>>
    @GET("kelas/{courseId}")
    suspend fun getCompletedCourse(@Path("userId") userId: Int): Response<List<CourseJson>>
    @POST("enrollClass")
    suspend fun enrollUserToCourse(@Body enrollmentRequest: EnrollmentRequest): Response<BasicResponse>

    // materi
    @GET("materi")
    suspend fun getMaterialsByCourse(@Query("kelas_id") kelas_id: Int): Response<List<MaterialJson>>
    @GET("materi/{materiId}")
    suspend fun getMaterialById(@Path("materiId") materialId: Int): Response<MaterialJson>

    //quiz

}