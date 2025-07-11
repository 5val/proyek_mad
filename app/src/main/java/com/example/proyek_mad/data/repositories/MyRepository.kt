package com.example.proyek_mad.data.repositories

import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.data.Option
import com.example.proyek_mad.data.Question
import com.example.proyek_mad.data.Quiz
import com.example.proyek_mad.data.QuizAttempt
import com.example.proyek_mad.data.User
import com.example.proyek_mad.data.sources.remote.receive.BestScoreJson
import com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson
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

interface MyRepository {
    suspend fun login(request: LoginRequest): User
    suspend fun updateUser(user: User)
    suspend fun register(request: RegisterRequest): Result<BasicResponse>
    suspend fun editPengguna(userId: Int, request: EditPenggunaRequest): Result<BasicResponse>

    // Course and Enrollment Operations
    suspend fun getAllPublishedCourses(userId: Int): Result<List<Course>>
    suspend fun getCourseById(courseId: Int, userId: Int): Result<Course>
    suspend fun getOngoingCourse(userId: Int): Result<List<Course>>
    suspend fun getCompletedCourse(userId: Int): Result<List<Course>>
    suspend fun enrollUserToCourse(kelasId: Int, userId: Int): Result<BasicResponse>
    suspend fun getOfflineCourse(userId: Int):List<Course>
    suspend fun deleteCourse(course: Course, userId: Int)

    // Material Operations
    suspend fun getMaterialsByCourse(courseId: Int): Result<List<Module>>
    suspend fun getMaterialById(materialId: Int): Result<Module>
    suspend fun nextMateri(nextMateriRequest: NextMateriRequest)
    suspend fun downloadCourse(course: Course, userId: Int, modules:List<Module>)


    // quiz
    suspend fun getKuisKelas(kelas_id: Int): Result<Quiz>
    suspend fun getAllSoalKuis(kuis_id: Int): Result<List<Question>>
    suspend fun getSoalKuis(urutan_soal: Int, kuis_id: Int): Result<Question>
    suspend fun getPilihanSoal(soal_id: Int): Result<List<Option>>
    suspend fun getNilaiTerbaik(kelas_id: Int, user_id: Int): Result<BestScoreJson>
    suspend fun getAllKuisAttempt(kuis_id: Int, user_id: Int): Result<List<QuizAttempt>>
    suspend fun getKuisAttemptTerakhir(): Result<QuizAttempt>
    suspend fun getEnrollment(user_id: Int, kelas_id: Int): Result<EnrollmentJson>
    suspend fun startKuis(createQuizAttemptRequest: CreateQuizAttemptRequest, kuis_id: Int): Result<QuizAttempt>
    suspend fun nilaiKuis(scoreQuizRequest: ScoreQuizRequest, attempt_id: Int?): Result<EnrollmentJson>
    suspend fun jawabSoal(quizAnswerRequest: QuizAnswerRequest, soal_id: Int): Result<QuizAttempt>

    // gemini
    suspend fun askGemini(geminiRequest: GeminiRequest):Result<BasicResponse>
}