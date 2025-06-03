package com.example.proyek_mad.data.sources.remote
import com.example.proyek_mad.data.sources.remote.receive.BestScoreJson
import com.example.proyek_mad.data.sources.remote.receive.CourseJson
import com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson
import com.example.proyek_mad.data.sources.remote.receive.MaterialJson
import com.example.proyek_mad.data.sources.remote.receive.OptionJson
import com.example.proyek_mad.data.sources.remote.receive.QuestionJson
import com.example.proyek_mad.data.sources.remote.receive.QuizAttemptJson
import com.example.proyek_mad.data.sources.remote.receive.QuizJson
import com.example.proyek_mad.data.sources.remote.receive.UserJson
import com.example.proyek_mad.data.sources.remote.request.CreateQuizAttemptRequest
import com.example.proyek_mad.data.sources.remote.request.EditPenggunaRequest
import com.example.proyek_mad.data.sources.remote.request.EnrollmentRequest
import com.example.proyek_mad.data.sources.remote.request.LoginRequest
import com.example.proyek_mad.data.sources.remote.request.QuizAnswerRequest
import com.example.proyek_mad.data.sources.remote.request.RegisterRequest
import com.example.proyek_mad.data.sources.remote.request.ScoreQuizRequest
import com.example.proyek_mad.data.sources.remote.response.BasicResponse
import com.example.proyek_mad.data.sources.remote.response.BestScoreResponse
import com.example.proyek_mad.data.sources.remote.response.EnrollmentResponse
import com.example.proyek_mad.data.sources.remote.response.OptionResponse
import com.example.proyek_mad.data.sources.remote.response.QuestionResponse
import com.example.proyek_mad.data.sources.remote.response.QuizAnswerResponse
import com.example.proyek_mad.data.sources.remote.response.QuizAttemptListResponse
import com.example.proyek_mad.data.sources.remote.response.QuizAttemptResponse
import com.example.proyek_mad.data.sources.remote.response.QuizResponse
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
    @GET("kuis/{kelas_id}")
    suspend fun getKuisKelas(@Path("kelas_id") kelas_id: Int): Response<QuizJson>

    @GET("soal/{kuis_id}")
    suspend fun getSoalKuis(@Query("urutan_soal") urutan_soal: Int, @Path("kuis_id") kuis_id: Int): Response<QuestionJson>

    @GET("all_soal/{kuis_id}")
    suspend fun getAllSoalKuis(@Path("kuis_id") kuis_id: Int): Response<List<QuestionJson>>

    @GET("pilihan/{soal_id}")
    suspend fun getPilihanSoal(@Path("soal_id") soal_id: Int): Response<List<OptionJson>>

    @GET("max_nilai/{kelas_id}")
    suspend fun getNilaiTerbaik(@Path("kelas_id") kelas_id: Int, @Query("user_id") user_id: Int): Response<BestScoreJson>

    @GET("attempt/{user_id}")
    suspend fun getAllKuisAttempt(@Query("kuis_id") kuis_id: Int, @Path("user_id") user_id: Int): Response<List<QuizAttemptJson>>

    @GET("last_attempt")
    suspend fun getKuisAttemptTerakhir(): Response<QuizAttemptJson>

    @GET("enrollment/{kelas_id}")
    suspend fun getEnrollments(@Path("kelas_id") kelas_id: Int, @Query("user_id") user_id: Int): Response<EnrollmentJson>

    @POST("start_kuis/{kuis_id}")
    suspend fun startKuis(@Body createQuizAttemptRequest: CreateQuizAttemptRequest, @Path("kuis_id") kuis_id: Int): Response<QuizAttemptJson>

    @POST("nilai_kuis/{attempt_id}")
    suspend fun nilaiKuis(@Body scoreQuizRequest: ScoreQuizRequest, @Path("attempt_id") attempt_id: Int?): Response<EnrollmentJson>

    @POST("jawab_soal/{soal_id}")
    suspend fun jawabSoal(@Body quizAnswerRequest: QuizAnswerRequest, @Path("soal_id") soal_id: Int): Response<QuizAttemptJson>
}