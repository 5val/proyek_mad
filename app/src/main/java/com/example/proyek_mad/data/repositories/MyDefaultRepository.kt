package com.example.proyek_mad.data.repositories

import android.util.Log
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.data.Option
import com.example.proyek_mad.data.Question
import com.example.proyek_mad.data.Quiz
import com.example.proyek_mad.data.QuizAttempt
import com.example.proyek_mad.data.User
import com.example.proyek_mad.data.sources.remote.RemoteDataSource
import com.example.proyek_mad.data.sources.remote.receive.BestScoreJson
import com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson
import com.example.proyek_mad.data.sources.remote.receive.UserJson
import com.example.proyek_mad.data.sources.remote.request.CreateQuizAttemptRequest
import com.example.proyek_mad.data.sources.remote.request.EditPenggunaRequest
import com.example.proyek_mad.data.sources.remote.request.LoginRequest
import com.example.proyek_mad.data.sources.remote.request.QuizAnswerRequest
import com.example.proyek_mad.data.sources.remote.request.RegisterRequest
import com.example.proyek_mad.data.sources.remote.request.ScoreQuizRequest
import com.example.proyek_mad.data.sources.remote.response.BasicResponse

class MyDefaultRepository(
//    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
):MyRepository {
    override suspend fun login(request: LoginRequest): User {
        remoteDataSource.login(request).onSuccess {it->
            return it.toUser()
        }.onFailure {
           err->
            Log.e("error", err.toString(), )
        }


        return User(0, "", "", "", "")
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

}