package com.example.proyek_mad.data.repositories

import com.example.proyek_mad.data.*
import com.example.proyek_mad.data.sources.local.LocalDataSource
import com.example.proyek_mad.data.sources.remote.RemoteDataSource
import com.example.proyek_mad.data.sources.remote.receive.*
import com.example.proyek_mad.data.sources.remote.request.*
import com.example.proyek_mad.data.sources.remote.response.BasicResponse
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class MyDefaultRepositoryTest {
    private lateinit var repository: MyDefaultRepository
    private val localDataSource: LocalDataSource = mock()
    private val remoteDataSource: RemoteDataSource = mock()

    @Before
    fun setup() {
        repository = MyDefaultRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `register - returns success response`() = runTest {
        // Arrange
        val request = RegisterRequest("test@email.com", "password", "Test User")
        val response = BasicResponse("Success")
        whenever(remoteDataSource.register(request)).thenReturn(Result.success(response))

        // Act
        val result = repository.register(request)

        // Assert
        assert(result.isSuccess)
        assert(result.getOrNull()?.message == "Success")
    }

    @Test
    fun `editPengguna - updates user successfully`() = runTest {
        // Arrange
        val userId = 1
        val request = EditPenggunaRequest("New Name", "new@email.com", "photo.jpg")
        val response = BasicResponse("User updated")
        whenever(remoteDataSource.editPengguna(userId, request)).thenReturn(Result.success(response))

        // Act
        val result = repository.editPengguna(userId, request)

        // Assert
        assert(result.isSuccess)
        assert(result.getOrNull()?.message == "User updated")
    }

    @Test
    fun `getOngoingCourse - returns ongoing courses`() = runTest {
        // Arrange
        val userId = 1
        val courses = listOf(
            Course(1, "Ongoing Course", "Description", "image.jpg", 0, false)
        )
        whenever(remoteDataSource.getOngoingCourse(userId)).thenReturn(Result.success(courses))

        // Act
        val result = repository.getOngoingCourse(userId)

        // Assert
        assert(result.isSuccess)
        result.getOrNull()?.let {
            assert(it.size == 1)
            assert(it[0].title == "Ongoing Course")
        }
    }

    @Test
    fun `getCompletedCourse - returns completed courses`() = runTest {
        // Arrange
        val userId = 1
        val courses = listOf(
            Course(1, "Completed Course", "Description", "image.jpg", 100, true)
        )
        whenever(remoteDataSource.getCompletedCourse(userId)).thenReturn(Result.success(courses))

        // Act
        val result = repository.getCompletedCourse(userId)

        // Assert
        assert(result.isSuccess)
        result.getOrNull()?.let {
            assert(it.size == 1)
            assert(it[0].title == "Completed Course")
        }
    }

    @Test
    fun `getAllSoalKuis - returns quiz questions`() = runTest {
        // Arrange
        val kuisId = 1
        val questions = listOf(
            Question(1, "Question 1", 1, 1),
            Question(2, "Question 2", 2, 1)
        )
        whenever(remoteDataSource.getAllSoalKuis(kuisId)).thenReturn(Result.success(questions))

        // Act
        val result = repository.getAllSoalKuis(kuisId)

        // Assert
        assert(result.isSuccess)
        result.getOrNull()?.let {
            assert(it.size == 2)
            assert(it[0].text == "Question 1")
        }
    }

    @Test
    fun `startKuis - creates quiz attempt successfully`() = runTest {
        // Arrange
        val kuisId = 1
        val request = CreateQuizAttemptRequest(1, "2024-03-20")
        val quizAttempt = QuizAttempt(1, 1, 1, "2024-03-20", null, 0)
        whenever(remoteDataSource.startKuis(request, kuisId)).thenReturn(Result.success(quizAttempt))

        // Act
        val result = repository.startKuis(request, kuisId)

        // Assert
        assert(result.isSuccess)
        result.getOrNull()?.let {
            assert(it.id == 1)
            assert(it.score == 0)
        }
    }

    @Test
    fun `nilaiKuis - scores quiz successfully`() = runTest {
        // Arrange
        val attemptId = 1
        val request = ScoreQuizRequest(80)
        val response = EnrollmentJson(1, 1, 1, true, 80)
        whenever(remoteDataSource.nilaiKuis(request, attemptId)).thenReturn(Result.success(response))

        // Act
        val result = repository.nilaiKuis(request, attemptId)

        // Assert
        assert(result.isSuccess)
        result.getOrNull()?.let {
            assert(it.score == 80)
            assert(it.is_completed)
        }
    }

    @Test
    fun `askGemini - returns success response`() = runTest {
        // Arrange
        val request = GeminiRequest("What is Kotlin?")
        val response = BasicResponse("Answer provided")
        whenever(remoteDataSource.askGemini(request)).thenReturn(Result.success(response))

        // Act
        val result = repository.askGemini(request)

        // Assert
        assert(result.isSuccess)
        assert(result.getOrNull()?.message == "Answer provided")
    }

    @Test
    fun `downloadCourse - saves course and modules locally`() = runTest {
        // Arrange
        val course = Course(1, "Test Course", "Description", "image.jpg", 0, false)
        val modules = listOf(
            Module(1, "Module 1", "Content 1", 1, 1, false),
            Module(2, "Module 2", "Content 2", 2, 1, false)
        )

        // Act
        repository.downloadCourse(course, 1, modules)

        // Assert
        verify(localDataSource).insertCourse(any())
        verify(localDataSource, times(2)).insertModule(any())
    }

    @Test
    fun `getOfflineCourse - returns locally stored courses`() = runTest {
        // Arrange
        val userId = 1
        val courseEntities = listOf(
            course.toCourseEntity(userId)
        )
        whenever(localDataSource.getCoursesByUserId(userId)).thenReturn(courseEntities)

        // Act
        val result = repository.getOfflineCourse(userId)

        // Assert
        assert(result.size == 1)
        assert(result[0].title == "Test Course")
    }
}