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
import com.example.proyek_mad.data.sources.remote.request.GeminiRequest
import com.example.proyek_mad.data.sources.remote.request.LoginRequest
import com.example.proyek_mad.data.sources.remote.request.NextMateriRequest
import com.example.proyek_mad.data.sources.remote.request.QuizAnswerRequest
import com.example.proyek_mad.data.sources.remote.request.RegisterRequest
import com.example.proyek_mad.data.sources.remote.request.ScoreQuizRequest
import com.example.proyek_mad.data.sources.remote.response.BasicResponse

interface RemoteDataSource {
    suspend fun login(request: LoginRequest): Result<UserJson>
    suspend fun register(request: RegisterRequest): Result<BasicResponse>
    suspend fun editPengguna(userId: Int, request: EditPenggunaRequest): Result<BasicResponse>

    // Course and Enrollment Operations
    suspend fun getAllPublishedCourses(userId:Int): Result<List<CourseJson>>
    suspend fun getCourseById(courseId: Int, userId: Int): Result<CourseJson>
    suspend fun getOngoingCourse(userId: Int): Result<List<CourseJson>>
    suspend fun getCompletedCourse(userId: Int): Result<List<CourseJson>>
    suspend fun enrollUserToCourse(kelasId: Int, userId: Int): Result<BasicResponse>

    // Material Operations
    suspend fun getMaterialsByCourse(courseId: Int): Result<List<MaterialJson>>
    suspend fun getMaterialById(materialId: Int): Result<MaterialJson>
    suspend fun nextMateri(nextMateriRequest: NextMateriRequest)
    // quiz
    suspend fun getKuisKelas(kelas_id: Int): Result<QuizJson>
    suspend fun getSoalKuis(urutan_soal: Int, kuis_id: Int): Result<QuestionJson>
    suspend fun getAllSoalKuis(kuis_id: Int): Result<List<QuestionJson>>
    suspend fun getPilihanSoal(soal_id: Int): Result<List<OptionJson>>
    suspend fun getNilaiTerbaik(kelas_id: Int, user_id: Int): Result<BestScoreJson>
    suspend fun getAllKuisAttempt(kuis_id: Int, user_id: Int): Result<List<QuizAttemptJson>>
    suspend fun getKuisAttemptTerakhir(): Result<QuizAttemptJson>
    suspend fun getEnrollment(kelas_id: Int, user_id: Int): Result<EnrollmentJson>
    suspend fun startKuis(createQuizAttemptRequest: CreateQuizAttemptRequest, kuis_id: Int): Result<QuizAttemptJson>
    suspend fun nilaiKuis(scoreQuizRequest: ScoreQuizRequest, attempt_id: Int?): Result<EnrollmentJson>
    suspend fun jawabSoal(quizAnswerRequest: QuizAnswerRequest, soal_id: Int): Result<QuizAttemptJson>

    // gemini
    suspend fun askGemini(geminiRequest: GeminiRequest):Result<BasicResponse>
}