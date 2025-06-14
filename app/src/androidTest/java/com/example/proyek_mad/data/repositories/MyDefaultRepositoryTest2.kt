package com.example.proyek_mad.data.repositories

import com.example.proyek_mad.data.sources.local.LocalDataSource
import com.example.proyek_mad.data.sources.remote.RemoteDataSource
import org.junit.Assert.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class MyDefaultRepositoryTest2 {
    private lateinit var repository: MyDefaultRepository
    private val localDataSource: LocalDataSource = mock()
    private val remoteDataSource: RemoteDataSource = mock()

    @Before
    fun setup() {
        localDataSource = mockk
        repository = MyDefaultRepository(localDataSource, remoteDataSource)
    }
    @Test
    fun login() {
    }

    @Test
    fun updateUser() {
    }

    @Test
    fun register() {
    }

    @Test
    fun editPengguna() {
    }

    @Test
    fun getAllPublishedCourses() {
    }

    @Test
    fun getCourseById() {
    }

    @Test
    fun downloadCourse() {
    }

    @Test
    fun getOngoingCourse() {
    }

    @Test
    fun getCompletedCourse() {
    }

    @Test
    fun getOfflineCourse() {
    }

    @Test
    fun deleteCourse() {
    }

    @Test
    fun enrollUserToCourse() {
    }

    @Test
    fun getMaterialsByCourse() {
    }

    @Test
    fun getMaterialById() {
    }

    @Test
    fun nextMateri() {
    }

    @Test
    fun getKuisKelas() {
    }

    @Test
    fun getAllSoalKuis() {
    }

    @Test
    fun getSoalKuis() {
    }

    @Test
    fun getPilihanSoal() {
    }

    @Test
    fun getNilaiTerbaik() {
    }

    @Test
    fun getAllKuisAttempt() {
    }

    @Test
    fun getKuisAttemptTerakhir() {
    }

    @Test
    fun getEnrollment() {
    }

    @Test
    fun startKuis() {
    }

    @Test
    fun nilaiKuis() {
    }

    @Test
    fun jawabSoal() {
    }

    @Test
    fun askGemini() {
    }
}