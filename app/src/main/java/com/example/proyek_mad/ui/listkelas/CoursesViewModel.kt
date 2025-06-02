package com.example.proyek_mad.ui.listkelas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.repositories.MyRepository
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val myRepository: MyRepository
):ViewModel() {
    private val _courses = MutableLiveData<Result<List<Course>>>()
    val courses: LiveData<Result<List<Course>>>
        get() = _courses

    fun refresh() {
        getAll()
    }
    fun search(keyword:String){
        viewModelScope.launch {
            _courses.value = _courses.value?.onSuccess{item-> item.filter { it.nama_kelas.contains(keyword) }}
        }
    }
    fun getAll(){
        viewModelScope.launch {
            _courses.value = myRepository.getAllPublishedCourses()
        }

    }
}