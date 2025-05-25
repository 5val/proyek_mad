package com.example.proyek_mad.ui.listkelas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyek_mad.data.Course

class CoursesViewModel:ViewModel() {
    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>>
        get() = _courses

    fun refreshCourses() {

    }
}