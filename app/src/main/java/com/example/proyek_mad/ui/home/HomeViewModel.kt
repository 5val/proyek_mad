package com.example.proyek_mad.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.repositories.MyRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val myRepository: MyRepository
):ViewModel() {
    private val _ongoingCourses = MutableLiveData<Result<List<Course>>>()
    val ongoingCourses: LiveData<Result<List<Course>>>
        get() = _ongoingCourses

    private val _completedCourses = MutableLiveData<Result<List<Course>>>()
    val completedCourses:LiveData<Result<List<Course>>>
        get() = _completedCourses

    fun refresh(){

    }
    fun getAll(){
        viewModelScope.launch {
            _ongoingCourses.value = myRepository.getOngoingCourse(MockDB.currentUser.user_id)
            _completedCourses.value = myRepository.getCompletedCourse(MockDB.currentUser.user_id)
        }
    }
}