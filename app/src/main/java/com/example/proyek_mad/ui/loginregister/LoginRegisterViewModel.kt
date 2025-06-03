package com.example.proyek_mad.ui.loginregister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Quiz
import com.example.proyek_mad.data.User
import com.example.proyek_mad.data.repositories.MyRepository
import com.example.proyek_mad.data.sources.remote.request.LoginRequest
import com.example.proyek_mad.data.sources.remote.request.RegisterRequest
import com.example.proyek_mad.data.sources.remote.response.BasicResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginRegisterViewModel(
    private val myRepository: MyRepository
) : ViewModel() {
    private val registeredUsers = mutableListOf<User>()
    private val _loginSuccess = MutableLiveData<User>()
    val loginSuccess: LiveData<User>
        get() = _loginSuccess
    private val _registerMessage = MutableLiveData<Result<BasicResponse>>()
    val registerMessage: LiveData<Result<BasicResponse>>
        get() = _registerMessage


    fun isEmailRegistered(email: String): Boolean {
        return registeredUsers.any { it.email.equals(email, ignoreCase = true) }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _registerMessage.value = myRepository.register(RegisterRequest(email, email, password, name))
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginSuccess.value = myRepository.login(LoginRequest(email, password))
        }
    }
}