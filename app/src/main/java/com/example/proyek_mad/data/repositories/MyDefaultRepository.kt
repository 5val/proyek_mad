package com.example.proyek_mad.data.repositories

import android.util.Log
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.data.Option
import com.example.proyek_mad.data.Question
import com.example.proyek_mad.data.Quiz
import com.example.proyek_mad.data.QuizAttempt
import com.example.proyek_mad.data.User
import com.example.proyek_mad.data.sources.local.LocalDataSource
import com.example.proyek_mad.data.sources.remote.RemoteDataSource
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

class MyDefaultRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
):MyRepository {
    override suspend fun login(request: LoginRequest): User {
        remoteDataSource.login(request).onSuccess {it->
            localDataSource.insertUser(it.toUser().toUserEntity())
            return it.toUser()
        }.onFailure {
           err->
            Log.e("error", err.toString(), )
            val user = localDataSource.getUserByEmailAndPassword(request.username, request.password)
            if(user!=null){
                MockDB.onlineMode =false
                return user.toUser()
            }
        }
        return User(0, "", "", "", "")
    }

    override suspend fun updateUser(user: User) {
        localDataSource.insertUser(user.toUserEntity())
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

    override suspend fun getAllPublishedCourses(userId: Int): Result<List<Course>> {
        return remoteDataSource.getAllPublishedCourses(userId).map { list ->
            list.map { it.toCourse() }
        }
    }

    override suspend fun getCourseById(courseId: Int, userId: Int): Result<Course> {
        if(MockDB.onlineMode){
            return remoteDataSource.getCourseById(courseId, userId).map { it.toCourse() }
        } else{
            val course = localDataSource.getCourseById(courseId, userId)
            if(course != null){
                return Result.success(course.toCourse())
            }
        }
        return Result.failure(Exception("Something went wrong"))
    }

    override suspend fun downloadCourse(course: Course, userId: Int, modules:List<Module>){
        localDataSource.insertCourse(course.toCourseEntity(userId))
        modules.forEach { it->
            localDataSource.insertModule(it.toModuleEntity())
        }

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
    override suspend fun getOfflineCourse(userId: Int):List<Course>{
        return localDataSource.getCoursesByUserId(userId).map {
            it->it.toCourse()
        }
    }

    override suspend fun deleteCourse(course: Course, userId: Int) {
        localDataSource.deleteCourse(course.toCourseEntity(userId))
    }

    override suspend fun enrollUserToCourse(kelasId: Int, userId: Int): Result<BasicResponse> {
        return remoteDataSource.enrollUserToCourse(kelasId, userId)
    }

    // Material Operation
    override suspend fun getMaterialsByCourse(courseId: Int): Result<List<Module>> {
        if(MockDB.onlineMode){
            return remoteDataSource.getMaterialsByCourse(courseId).map { list->
                list.map { it.toMaterial() }
            }
        }
        var result = localDataSource.getModulesByCourseId(courseId).map {
            it.toModule()
        }
        if (result !=null){
            return Result.success(result)
        }
        return Result.failure(Exception("Something went wrong"))
    }

    override suspend fun getMaterialById(materialId: Int): Result<Module> {
        if(MockDB.onlineMode){
            return remoteDataSource.getMaterialById(materialId).map {
                it.toMaterial()
            }
        }
        var result = localDataSource.getModule(materialId)?.toModule()
        if (result !=null){
            return Result.success(result)
        }
        return Result.failure(Exception("Something went wrong"))
    }

    override suspend fun nextMateri(nextMateriRequest: NextMateriRequest) {
        if(MockDB.onlineMode){
            remoteDataSource.nextMateri(nextMateriRequest)
        }
    }
    // Quiz

    override suspend fun getKuisKelas(kelas_id: Int): Result<Quiz> {
        return remoteDataSource.getKuisKelas(kelas_id).map {
            it.toQuiz()
        }
    }

    override suspend fun getAllSoalKuis(kuis_id: Int): Result<List<Question>> {
        return remoteDataSource.getAllSoalKuis(kuis_id).map { list->
            list.map { it.toQuestion() }
        }
    }

    override suspend fun getSoalKuis(urutan_soal: Int, kuis_id: Int): Result<Question> {
        return remoteDataSource.getSoalKuis(urutan_soal, kuis_id).map {
            it.toQuestion()
        }
    }

    override suspend fun getPilihanSoal(soal_id: Int): Result<List<Option>> {
        return remoteDataSource.getPilihanSoal(soal_id).map { list->
            list.map { it.toOption() }
        }
    }

    override suspend fun getNilaiTerbaik(kelas_id: Int, user_id: Int): Result<BestScoreJson> {
        return remoteDataSource.getNilaiTerbaik(kelas_id, user_id)
    }

    override suspend fun getAllKuisAttempt(kuis_id: Int, user_id: Int): Result<List<QuizAttempt>> {
        return remoteDataSource.getAllKuisAttempt(kuis_id, user_id).map { list->
            list.map { it.toQuizAttempt() }
        }
    }

    override suspend fun getKuisAttemptTerakhir(): Result<QuizAttempt> {
        return remoteDataSource.getKuisAttemptTerakhir().map {
            it.toQuizAttempt()
        }
    }

    override suspend fun getEnrollment(kelas_id: Int, user_id: Int): Result<EnrollmentJson> {
        return remoteDataSource.getEnrollment(kelas_id, user_id)
    }

    override suspend fun startKuis(
        createQuizAttemptRequest: CreateQuizAttemptRequest,
        kuis_id: Int
    ): Result<QuizAttempt> {
        return remoteDataSource.startKuis(createQuizAttemptRequest, kuis_id).map {
            it.toQuizAttempt()
        }
    }

    override suspend fun nilaiKuis(
        scoreQuizRequest: ScoreQuizRequest,
        attempt_id: Int?
    ): Result<EnrollmentJson> {
        return remoteDataSource.nilaiKuis(scoreQuizRequest, attempt_id)
    }

    override suspend fun jawabSoal(
        quizAnswerRequest: QuizAnswerRequest,
        soal_id: Int
    ): Result<QuizAttempt> {
        return remoteDataSource.jawabSoal(quizAnswerRequest, soal_id).map {
            it.toQuizAttempt()
        }
    }

    // gemini
    override suspend fun askGemini(geminiRequest: GeminiRequest): Result<BasicResponse> {
        return remoteDataSource.askGemini(geminiRequest)
    }

}