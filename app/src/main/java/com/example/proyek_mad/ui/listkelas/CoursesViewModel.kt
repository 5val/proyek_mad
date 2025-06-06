package com.example.proyek_mad.ui.listkelas

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.repositories.MyRepository
import com.example.proyek_mad.data.sources.remote.response.BasicResponse
import kotlinx.coroutines.launch

class CoursesViewModel(
    private val myRepository: MyRepository
):ViewModel() {
    private val _courses = MutableLiveData<Result<List<Course>>>()
    val courses: LiveData<Result<List<Course>>>
        get() = _courses
    private val _message = MutableLiveData<Result<BasicResponse>>()
    val message: LiveData<Result<BasicResponse>>
        get() = _message

    fun refresh() {
        getAll()
    }
    fun search(keyword:String){
        if(keyword==""){
            refresh()
            return
        }
        _courses.value = _courses.value?.map { item ->
            item.filter {
                it.nama_kelas.contains(keyword)
            }
        }
    }
    fun getAll(){
        viewModelScope.launch {
            _courses.value = myRepository.getAllPublishedCourses(MockDB.currentUser.user_id)
        }
    }
    fun clickClass(course: Course){
        viewModelScope.launch {
            MockDB.selectedKelas = course.kelas_id
            if(course.attended =="belum"){
                _message.value = myRepository.enrollUserToCourse(course.kelas_id, MockDB.currentUser.user_id)
            } else{
                _message.value = Result.success(BasicResponse("Success"))
            }
        }

    }
}