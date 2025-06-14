package com.example.proyek_mad.data.repositories

import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.data.Option
import com.example.proyek_mad.data.Question
import com.example.proyek_mad.data.Quiz
import com.example.proyek_mad.data.QuizAttempt
import com.example.proyek_mad.data.User
import com.example.proyek_mad.data.repositories.MyDefaultRepository
import com.example.proyek_mad.data.sources.local.Course.CourseEntity
import com.example.proyek_mad.data.sources.local.LocalDataSource
import com.example.proyek_mad.data.sources.local.Module.ModuleEntity
import com.example.proyek_mad.data.sources.local.User.UserEntity
import com.example.proyek_mad.data.sources.remote.RemoteDataSource
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
import io.mockk.Runs
import org.junit.Assert.*

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import java.util.Date


class MyDefaultRepositoryTest2 {

    // Mocks for the data sources
    private val localDataSource: LocalDataSource = mockk()
    private val remoteDataSource: RemoteDataSource = mockk()

    // The repository instance under test
    private lateinit var repository: MyDefaultRepository

    // Dummy data for testing
    private lateinit var dummyUser: User
    private lateinit var dummyUserEntity: UserEntity
    private lateinit var dummyRemoteUser: UserJson // Assuming a remote user model
    private lateinit var dummyLoginRequest: LoginRequest
    private lateinit var dummyRegisterRequest: RegisterRequest
    private lateinit var dummyEditPenggunaRequest: EditPenggunaRequest
    private lateinit var dummyCourse: Course
    private lateinit var dummyCourseEntity: CourseEntity // Assuming a course entity model
    private lateinit var dummyRemoteCourse: CourseJson // Assuming a remote course model
    private lateinit var dummyModule: Module
    private lateinit var dummyModuleEntity: ModuleEntity // Assuming a module entity model
    private lateinit var dummyRemoteModule: MaterialJson // Assuming a remote module model
    private lateinit var dummyQuiz: QuizJson
    private lateinit var dummyQuestion: QuestionJson
    private lateinit var dummyOption: OptionJson
    private lateinit var dummyQuizAttempt: QuizAttemptJson
    private lateinit var dummyEnrollmentJson: EnrollmentJson
    private lateinit var dummyBestScoreJson: BestScoreJson
    private lateinit var dummyBasicResponse: BasicResponse
    private lateinit var dummyGeminiRequest: GeminiRequest


    @Before
    fun setUp() {
        // Reset mocks before each test
        unmockkAll()

        // Initialize the repository with mocks
        repository = MyDefaultRepository(localDataSource, remoteDataSource)

        // Initialize Dummy Storage
        dummyUser = User(1, "test@example.com", "Test User", "password", "test")
        dummyUserEntity = UserEntity(1, "test@example.com", "Test User", "password", "")
        dummyRemoteUser = UserJson(1, "test@example.com", "Test User", "password", "test")
        dummyLoginRequest = LoginRequest("test@example.com", "password")
        dummyRegisterRequest = RegisterRequest("Test User", "test@example.com", "password", "")
        dummyEditPenggunaRequest = EditPenggunaRequest("Updated User", "newpassword")
        dummyBasicResponse = BasicResponse("Success")
        dummyGeminiRequest = GeminiRequest("What is Kotlin?")

        dummyCourse = Course(1, "Kotlin Basics", "Learn Kotlin from scratch",  1, 100, "selesai")
        dummyCourseEntity = CourseEntity(1, 1, "Kotlin Basics", "Learn Kotlin from scratch",  null, 100)
        dummyRemoteCourse = CourseJson(1, "Kotlin Basics", "Learn Kotlin from scratch", null, 100, "selesai")


        dummyModule = Module(1, 1, "Variables", "content", 1, "")
        dummyModuleEntity = ModuleEntity(1, 1,"Variables", "content", "", 1)
        dummyRemoteModule = MaterialJson(1, 1,"Variables", "content", "", 1, "", "")


        dummyQuiz = QuizJson(1, 1, "Final Quiz", "5", 80, "", "")
        dummyQuestion = QuestionJson(1, 1,"What is a val?", 1, 20, "", "")
        dummyOption = OptionJson(1, 1,"A variable",  1, "", "")
        dummyQuizAttempt = QuizAttemptJson(1, 1, 1, 0, null, null, "", "")
        dummyEnrollmentJson = EnrollmentJson(1, 1, 1, "2025-06-10", "2025-06-15", "selesai", 5, 100.0f, Date().toString(), Date().toString())
        dummyBestScoreJson = BestScoreJson(1, 1, 100)


        // Mock the to...() extension functions
        // This is a common pattern, but you might need to adjust based on your actual extension function definitions
        // For simplicity, we assume they are top-level functions that can be mocked if needed,
        // or we just trust their simple mapping logic. Here we'll just use the initialized objects.
    }

    // --- User & Auth ---

    @Test
    fun login_remoteSuccess() = runTest {
        // Arrange
        val remoteResult = Result.success(dummyRemoteUser)
        coEvery { remoteDataSource.login(any()) } returns remoteResult
        coEvery { localDataSource.insertUser(any()) } returns dummyUser.toUserEntity()

        // Act
        val result = repository.login(dummyLoginRequest)

        // Assert
        assertEquals(dummyUser.user_id, result.user_id)
        assertEquals(dummyUser.email, result.email)
        coVerify { remoteDataSource.login(dummyLoginRequest) }
        coVerify { localDataSource.insertUser(dummyUser.toUserEntity()) }
    }

    @Test
    fun login_remoteFailure_localSuccess() = runTest {
        // Arrange
        val remoteResult = Result.failure<UserJson>(Exception("Network Error"))
        coEvery { remoteDataSource.login(any()) } returns remoteResult
        coEvery { localDataSource.getUserByEmailAndPassword(any(), any()) } returns dummyUserEntity

        // Act
        val result = repository.login(dummyLoginRequest)

        // Assert
        assertFalse(MockDB.onlineMode) // Check if offline mode was triggered
        assertEquals(dummyUser.user_id, result.user_id)
        assertEquals(dummyUser.email, result.email)
        coVerify { remoteDataSource.login(dummyLoginRequest) }
        coVerify { localDataSource.getUserByEmailAndPassword(dummyLoginRequest.username, dummyLoginRequest.password) }
        coVerify(exactly = 0) { localDataSource.insertUser(any()) } // Should not insert on local read
    }

    @Test
    fun updateUser() = runTest {
        coEvery { localDataSource.insertUser(any()) } returns dummyUser.toUserEntity()

        repository.updateUser(dummyUser)

        coVerify { localDataSource.insertUser(dummyUser.toUserEntity()) }
    }

    @Test
    fun register() = runTest {
        coEvery { remoteDataSource.register(any()) } returns Result.success(dummyBasicResponse)

        val result = repository.register(dummyRegisterRequest)

        assertTrue(result.isSuccess)
        assertEquals(dummyBasicResponse, result.getOrNull())
        coVerify { remoteDataSource.register(dummyRegisterRequest) }
    }

    @Test
    fun editPengguna() = runTest {
        coEvery { remoteDataSource.editPengguna(any(), any()) } returns Result.success(dummyBasicResponse)

        val result = repository.editPengguna(1, dummyEditPenggunaRequest)

        assertTrue(result.isSuccess)
        assertEquals(dummyBasicResponse, result.getOrNull())
        coVerify { remoteDataSource.editPengguna(1, dummyEditPenggunaRequest) }
    }


    // --- Course Operations ---

    @Test
    fun getAllPublishedCourses() = runTest {
        val remoteCourses = listOf(dummyRemoteCourse)
        coEvery { remoteDataSource.getAllPublishedCourses(any()) } returns Result.success(remoteCourses)

        val result = repository.getAllPublishedCourses(1)

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        assertEquals(dummyCourse.nama_kelas, result.getOrNull()?.first()?.nama_kelas)
        coVerify { remoteDataSource.getAllPublishedCourses(1) }
    }

    @Test
    fun getCourseById_onlineMode() = runTest {
        MockDB.onlineMode = true
        coEvery { remoteDataSource.getCourseById(any(), any()) } returns Result.success(dummyRemoteCourse)

        val result = repository.getCourseById(1, 1)

        assertTrue(result.isSuccess)
        assertEquals(dummyCourse.kelas_id, result.getOrNull()?.kelas_id)
        coVerify { remoteDataSource.getCourseById(1, 1) }
    }

    @Test
    fun getCourseById_offlineMode() = runTest {
        MockDB.onlineMode = false
        coEvery { localDataSource.getCourseById(any(), any()) } returns dummyCourseEntity

        val result = repository.getCourseById(1, 1)

        assertTrue(result.isSuccess)
        assertEquals(dummyCourse.kelas_id, result.getOrNull()?.kelas_id)
        coVerify { localDataSource.getCourseById(1, 1) }
        coVerify(exactly = 0) { remoteDataSource.getCourseById(any(), any()) }
    }


    @Test
    fun downloadCourse() = runTest {
        coEvery { localDataSource.insertCourse(any()) } returns dummyCourse.toCourseEntity(1)
        coEvery { localDataSource.insertModule(any()) } returns dummyModule.toModuleEntity()

        repository.downloadCourse(dummyCourse, 1, listOf(dummyModule))

        coVerify { localDataSource.insertCourse(dummyCourse.toCourseEntity(1)) }
        coVerify { localDataSource.insertModule(dummyModule.toModuleEntity()) }
    }

    @Test
    fun getOngoingCourse() = runTest {
        coEvery { remoteDataSource.getOngoingCourse(any()) } returns Result.success(listOf(dummyRemoteCourse))

        val result = repository.getOngoingCourse(1)

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        coVerify { remoteDataSource.getOngoingCourse(1) }
    }

    @Test
    fun getCompletedCourse() = runTest {
        coEvery { remoteDataSource.getCompletedCourse(any()) } returns Result.success(listOf(dummyRemoteCourse))

        val result = repository.getCompletedCourse(1)

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        coVerify { remoteDataSource.getCompletedCourse(1) }
    }


    @Test
    fun getOfflineCourse() = runTest {
        coEvery { localDataSource.getCoursesByUserId(any()) } returns listOf(dummyCourseEntity)

        val courses = repository.getOfflineCourse(1)

        assertEquals(1, courses.size)
        assertEquals(dummyCourse.nama_kelas, courses[0].nama_kelas)
        coVerify { localDataSource.getCoursesByUserId(1) }
    }

    @Test
    fun deleteCourse() = runTest {
        coEvery { localDataSource.deleteCourse(any()) } returns dummyCourse.toCourseEntity(1)

        repository.deleteCourse(dummyCourse, 1)

        coVerify { localDataSource.deleteCourse(dummyCourse.toCourseEntity(1)) }
    }

    @Test
    fun enrollUserToCourse() = runTest {
        coEvery { remoteDataSource.enrollUserToCourse(any(), any()) } returns Result.success(dummyBasicResponse)

        val result = repository.enrollUserToCourse(1, 1)

        assertTrue(result.isSuccess)
        coVerify { remoteDataSource.enrollUserToCourse(1, 1) }
    }

    // --- Material Operations ---

    @Test
    fun getMaterialsByCourse_onlineMode() = runTest {
        MockDB.onlineMode = true
        coEvery { remoteDataSource.getMaterialsByCourse(any()) } returns Result.success(listOf(dummyRemoteModule))

        val result = repository.getMaterialsByCourse(1)

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        coVerify { remoteDataSource.getMaterialsByCourse(1) }
    }

    @Test
    fun getMaterialsByCourse_offlineMode() = runTest {
        MockDB.onlineMode = false
        coEvery { localDataSource.getModulesByCourseId(any()) } returns listOf(dummyModuleEntity)

        val result = repository.getMaterialsByCourse(1)

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        coVerify { localDataSource.getModulesByCourseId(1) }
//        coVerify(exactly = 0) { remoteDataSource.getMaterialsByCourse(any()) }
    }


    @Test
    fun getMaterialById_onlineMode() = runTest {
        MockDB.onlineMode = true
        coEvery { remoteDataSource.getMaterialById(any()) } returns Result.success(dummyRemoteModule)

        val result = repository.getMaterialById(1)

        assertTrue(result.isSuccess)
        assertEquals(dummyModule.materi_id, result.getOrNull()?.materi_id)
        coVerify { remoteDataSource.getMaterialById(1) }
    }

    @Test
    fun getMaterialById_offlineMode() = runTest {
        MockDB.onlineMode = false
        coEvery { localDataSource.getModule(any()) } returns dummyModuleEntity

        val result = repository.getMaterialById(1)

        assertTrue(result.isSuccess)
        assertEquals(dummyModule.materi_id, result.getOrNull()?.materi_id)
        coVerify { localDataSource.getModule(1) }
    }

    @Test
    fun nextMateri() = runTest {
        MockDB.onlineMode = true
        val request = NextMateriRequest(1, 1, 1)
        coEvery { remoteDataSource.nextMateri(any()) } just Runs

        repository.nextMateri(request)

        coVerify { remoteDataSource.nextMateri(request) }
    }


    // --- Quiz Operations ---

    @Test
    fun getKuisKelas() = runTest {
        coEvery { remoteDataSource.getKuisKelas(any()) } returns Result.success(dummyQuiz)

        val result = repository.getKuisKelas(1)

        assertTrue(result.isSuccess)
        val quizzes = dummyQuiz.toQuiz()
        assertEquals(quizzes, result.getOrNull())
        coVerify { remoteDataSource.getKuisKelas(1) }
    }

    @Test
    fun getAllSoalKuis() = runTest {
        coEvery { remoteDataSource.getAllSoalKuis(any()) } returns Result.success(listOf(dummyQuestion))

        val result = repository.getAllSoalKuis(1)

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        coVerify { remoteDataSource.getAllSoalKuis(1) }
    }

    @Test
    fun getSoalKuis() = runTest {
        coEvery { remoteDataSource.getSoalKuis(any(), any()) } returns Result.success(dummyQuestion)

        val result = repository.getSoalKuis(1, 1)

        assertTrue(result.isSuccess)
        val questions = dummyQuestion.toQuestion()
        assertEquals(questions, result.getOrNull())
        coVerify { remoteDataSource.getSoalKuis(1, 1) }
    }

    @Test
    fun getPilihanSoal() = runTest {
        coEvery { remoteDataSource.getPilihanSoal(any()) } returns Result.success(listOf(dummyOption))

        val result = repository.getPilihanSoal(1)

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        coVerify { remoteDataSource.getPilihanSoal(1) }
    }

    @Test
    fun getNilaiTerbaik() = runTest {
        coEvery { remoteDataSource.getNilaiTerbaik(any(), any()) } returns Result.success(dummyBestScoreJson)

        val result = repository.getNilaiTerbaik(1, 1)

        assertTrue(result.isSuccess)
        assertEquals(dummyBestScoreJson, result.getOrNull())
        coVerify { remoteDataSource.getNilaiTerbaik(1, 1) }
    }

    @Test
    fun getAllKuisAttempt() = runTest {
        coEvery { remoteDataSource.getAllKuisAttempt(any(), any()) } returns Result.success(listOf(dummyQuizAttempt))

        val result = repository.getAllKuisAttempt(1, 1)

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        coVerify { remoteDataSource.getAllKuisAttempt(1, 1) }
    }

    @Test
    fun getKuisAttemptTerakhir() = runTest {
        coEvery { remoteDataSource.getKuisAttemptTerakhir() } returns Result.success(dummyQuizAttempt)

        val result = repository.getKuisAttemptTerakhir()

        assertTrue(result.isSuccess)
        val quizAttempts = dummyQuizAttempt.toQuizAttempt()
        assertEquals(quizAttempts, result.getOrNull())
        coVerify { remoteDataSource.getKuisAttemptTerakhir() }
    }

    @Test
    fun getEnrollment() = runTest {
        coEvery { remoteDataSource.getEnrollment(any(), any()) } returns Result.success(dummyEnrollmentJson)

        val result = repository.getEnrollment(1, 1)

        assertTrue(result.isSuccess)
        assertEquals(dummyEnrollmentJson, result.getOrNull())
        coVerify { remoteDataSource.getEnrollment(1, 1) }
    }

    @Test
    fun startKuis() = runTest {
        val request = CreateQuizAttemptRequest(1, 1)
        coEvery { remoteDataSource.startKuis(any(), any()) } returns Result.success(dummyQuizAttempt)

        val result = repository.startKuis(request, 1)

        assertTrue(result.isSuccess)

        val quizAttempts = dummyQuizAttempt.toQuizAttempt()
        assertEquals(quizAttempts, result.getOrNull())
        coVerify { remoteDataSource.startKuis(request, 1) }
    }

    @Test
    fun nilaiKuis() = runTest {
        val request = ScoreQuizRequest(100, 1)
        coEvery { remoteDataSource.nilaiKuis(any(), any()) } returns Result.success(dummyEnrollmentJson)

        val result = repository.nilaiKuis(request, 1)

        assertTrue(result.isSuccess)
        assertEquals(dummyEnrollmentJson, result.getOrNull())
        coVerify { remoteDataSource.nilaiKuis(request, 1) }
    }

    @Test
    fun jawabSoal() = runTest {
        val request = QuizAnswerRequest(1, 1, 1)
        coEvery { remoteDataSource.jawabSoal(any(), any()) } returns Result.success(dummyQuizAttempt)

        val result = repository.jawabSoal(request, 1)

        assertTrue(result.isSuccess)
        val quizAttempts = dummyQuizAttempt.toQuizAttempt()
        assertEquals(quizAttempts, result.getOrNull())
        coVerify { remoteDataSource.jawabSoal(request, 1) }
    }

    // --- Gemini ---

    @Test
    fun askGemini() = runTest {
        coEvery { remoteDataSource.askGemini(any()) } returns Result.success(dummyBasicResponse)

        val result = repository.askGemini(dummyGeminiRequest)

        assertTrue(result.isSuccess)
        assertEquals("Success", result.getOrNull()?.msg)
        coVerify { remoteDataSource.askGemini(dummyGeminiRequest) }
    }
}

// NOTE: You need to define the dummy data classes (User, Course, LoginRequest, etc.),
// the to...() extension functions, and the MockDB object for this code to be complete.
// For example:
//object MockDB {
//    var onlineMode = true
//}

// And assumed data classes and mappers:
// data class User(val id: Int, val email: String, val name: String, val token: String, val other: String)
// fun User.toUserEntity(): UserEntity { ... }
// data class RemoteUser(val id: Int, val email: String, val name: String, val token: String)
// fun RemoteUser.toUser(): User { ... }
// etc.