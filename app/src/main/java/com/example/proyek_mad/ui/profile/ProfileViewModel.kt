package com.example.proyek_mad.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.User
import com.example.proyek_mad.data.repositories.MyRepository
import com.example.proyek_mad.data.sources.remote.request.EditPenggunaRequest
import com.example.proyek_mad.data.sources.remote.request.LoginRequest
import com.example.proyek_mad.data.sources.remote.response.BasicResponse
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val myRepository: MyRepository
):ViewModel() {
    private val _profile = MutableLiveData<User>()
    val profile: LiveData<User>
        get() = _profile
    private val _editSuccess = MutableLiveData<Result<BasicResponse>>()
    val editSuccess: LiveData<Result<BasicResponse>>
        get() = _editSuccess
    private val _completedCourses = MutableLiveData<Result<List<Course>>>()
    val completedCourses:LiveData<Result<List<Course>>>
        get() = _completedCourses
    fun init(){
        viewModelScope.launch {
            _profile.value = MockDB.currentUser
        }
    }
    fun updateProfile(name:String, password:String){
        viewModelScope.launch {
            _editSuccess.value = myRepository.editPengguna(
                MockDB.currentUser.user_id,
                EditPenggunaRequest(
                    password,
                    name
                )
            )
        }
    }
    fun updateMockDB(){
        viewModelScope.launch {
            MockDB.currentUser = myRepository.login(LoginRequest(MockDB.currentUser.email, MockDB.currentUser.password))
        }
    }
    fun validatePassword(password:String, confirmPassword:String):Boolean{
        if(password == confirmPassword){
            return true
        }
        return false
    }
    fun getCompletedCourses(){
        viewModelScope.launch {
            _completedCourses.value = myRepository.getCompletedCourse(MockDB.currentUser.user_id)
        }
    }
}