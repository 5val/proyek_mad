package com.example.proyek_mad.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyek_mad.data.Course

class HomeViewModel:ViewModel() {
    private val _ongoingCourses = MutableLiveData<List<Course>>()
    val ongoingCourses: LiveData<List<Course>>
        get() = _ongoingCourses

    private val _completedCourses = MutableLiveData<List<Course>>()
    val completedCourses:LiveData<List<Course>>
        get() = _completedCourses

    fun refresh(){

    }
    fun getAll(){
        
    }
}