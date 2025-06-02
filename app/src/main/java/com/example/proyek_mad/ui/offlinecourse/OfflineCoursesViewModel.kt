package com.example.proyek_mad.ui.offlinecourse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.repositories.MyRepository
import kotlinx.coroutines.launch

class OfflineCoursesViewModel(
    private val myRepository: MyRepository
):ViewModel() {
    private val _offlineCourses = MutableLiveData<List<Course>>()
    val offlineCourses: LiveData<List<Course>>
        get() = _offlineCourses

    fun refresh(){
        getAll()
    }
    fun getAll(){
        viewModelScope.launch {
            _offlineCourses.value = MockDB.courses
        }
    }
    fun deleteCourse(course:Course){
        MockDB.courses = MockDB.courses.filter { it.kelas_id != course.kelas_id }
        refresh()
    }
}