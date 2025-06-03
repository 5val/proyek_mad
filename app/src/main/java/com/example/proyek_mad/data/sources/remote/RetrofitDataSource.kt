package com.example.proyek_mad.data.sources.remote

import android.util.Log
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
    override suspend fun getAllPublishedCourses(userId: Int): Result<List<CourseJson>> {
        return safeApiCall { apiService.getAllPublishedCourses(userId) }
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

    override suspend fun getKuisKelas(kelas_id: Int): Result<QuizJson> {
        return safeApiCall { apiService.getKuisKelas(kelas_id) }
    }

    override suspend fun getSoalKuis(urutan_soal: Int, kuis_id: Int): Result<QuestionJson> {
        return safeApiCall { apiService.getSoalKuis(urutan_soal, kuis_id) }
    }

    override suspend fun getAllSoalKuis(kuis_id: Int): Result<List<QuestionJson>> {
        return safeApiCall { apiService.getAllSoalKuis(kuis_id) }
    }

    override suspend fun getPilihanSoal(soal_id: Int): Result<List<OptionJson>> {
        return safeApiCall { apiService.getPilihanSoal(soal_id) }
    }

    override suspend fun getNilaiTerbaik(kelas_id: Int, user_id: Int): Result<BestScoreJson> {
        return safeApiCall { apiService.getNilaiTerbaik(kelas_id, user_id) }
    }

    override suspend fun getAllKuisAttempt(
        kuis_id: Int,
        user_id: Int
    ): Result<List<QuizAttemptJson>> {
        return safeApiCall { apiService.getAllKuisAttempt(kuis_id, user_id) }
    }

    override suspend fun getKuisAttemptTerakhir(): Result<QuizAttemptJson> {
        return safeApiCall { apiService.getKuisAttemptTerakhir() }
    }

    override suspend fun getEnrollment(kelas_id: Int, user_id: Int): Result<EnrollmentJson> {
        Log.e("debug", safeApiCall { apiService.getEnrollments(user_id, kelas_id) }.toString() )
        return safeApiCall { apiService.getEnrollments(user_id, kelas_id) }
    }

    override suspend fun startKuis(
        createQuizAttemptRequest: CreateQuizAttemptRequest,
        kuis_id: Int
    ): Result<QuizAttemptJson> {
        return safeApiCall { apiService.startKuis(createQuizAttemptRequest, kuis_id) }
    }

    override suspend fun nilaiKuis(
        scoreQuizRequest: ScoreQuizRequest,
        attempt_id: Int?
    ): Result<EnrollmentJson> {
        return safeApiCall { apiService.nilaiKuis(scoreQuizRequest, attempt_id) }
    }

    override suspend fun jawabSoal(
        quizAnswerRequest: QuizAnswerRequest,
        soal_id: Int
    ): Result<QuizAttemptJson> {
        return safeApiCall { apiService.jawabSoal(quizAnswerRequest, soal_id) }
    }
}