package com.example.proyek_mad.ui.detailkelas

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.data.repositories.MyRepository
import com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson
import kotlinx.coroutines.launch

class CourseDetailViewModel(
    private val myRepository: MyRepository
):ViewModel() {
    private val _materi = MutableLiveData<Result<List<Module>>>()
    val materi: LiveData<Result<List<Module>>>
        get() = _materi
    private val _enrollment = MutableLiveData<Result<EnrollmentJson>>()
    val enrollment: LiveData<Result<EnrollmentJson>>
        get() = _enrollment

    private val _nilaiTerbaik = MutableLiveData<Int>(0)
    val nilaiTerbaik: LiveData<Int>
        get() = _nilaiTerbaik
    private val _currentCourse = MutableLiveData<Result<Course>>()
    val currentCourse: LiveData<Result<Course>>
        get() = _currentCourse


    fun fetchEnrollment(){
        viewModelScope.launch {
            _enrollment.value = myRepository.getEnrollment(MockDB.currentUser.user_id, MockDB.selectedKelas)
        }
    }
    fun refresh() {
        viewModelScope.launch {
            getCourse()
            getModule()
//            var kuisKelas = MockDB.quizzes.find { it.kelas_id == MockDB.selectedKelas }
//            var kuisKelas = myRepository.getKuisKelas(MockDB.selectedKelas).getOrNull()
//            var kuisAttempts = MockDB.quizAttempts.filter { it.kuis_id == kuisKelas?.kuis_id && it.user_id == MockDB.currentUser.user_id}.sortedByDescending {
//                it.skor_diperoleh
//            }
//            if(kuisAttempts.size > 0) {
//                nilaiTerbaik = kuisAttempts[0].skor_diperoleh
//            }
            _nilaiTerbaik.value = myRepository.getNilaiTerbaik(MockDB.selectedKelas, MockDB.currentUser.user_id).getOrNull()?.nilai?: 0
        }
    }
    fun getCourse(){
        viewModelScope.launch {
            _currentCourse.value = myRepository.getCourseById(MockDB.selectedKelas, MockDB.currentUser.user_id)
        }
    }
    fun getModule(){
        viewModelScope.launch {
            _materi.value = myRepository.getMaterialsByCourse(MockDB.selectedKelas)
        }
    }
    fun downloadCourse(){
        viewModelScope.launch {
            myRepository.downloadCourse(
                _currentCourse.value?.getOrNull()?:Course(0, "", "", 0, 0, ""),
                MockDB.currentUser.user_id,
                _materi.value?.getOrNull()?: listOf<Module>())
        }
    }
}