package com.example.proyek_mad.ui.listkelas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import kotlinx.coroutines.launch

class CoursesViewModel:ViewModel() {
    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>>
        get() = _courses

    fun refresh() {
        getAll()
    }
    fun search(keyword:String){
        viewModelScope.launch {
            _courses.value = MockDB.courses.filter { it.nama_kelas.contains(keyword) }
        }
    }
    fun getAll(){
        viewModelScope.launch {
            _courses.value = MockDB.courses
        }

    }
}