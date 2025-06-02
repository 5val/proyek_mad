package com.example.proyek_mad.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.User
import com.example.proyek_mad.data.repositories.MyRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val myRepository: MyRepository
):ViewModel() {
    private val _profile = MutableLiveData<User>()
    val profile: LiveData<User>
        get() = _profile
    fun init(){
        viewModelScope.launch {
            _profile.value = MockDB.currentUser
        }
    }
    fun updateProfile(name:String, password:String){
        viewModelScope.launch {
            MockDB.currentUser.nama_lengkap = name
            MockDB.currentUser.password = password
        }
    }
    fun validatePassword(password:String, confirmPassword:String):Boolean{
        if(password == confirmPassword){
            return true
        }
        return false
    }
}