package com.example.proyek_mad.ui.loginregister

import androidx.lifecycle.ViewModel
import com.example.proyek_mad.data.User

class LoginRegisterViewModel : ViewModel() {
    private val registeredUsers = mutableListOf<User>()

    fun isEmailRegistered(email: String): Boolean {
        return registeredUsers.any { it.email.equals(email, ignoreCase = true) }
    }

    fun register(name: String, email: String, password: String) {
        val newUser = User(0, "", email, password, name)
        registeredUsers.add(newUser)
    }

    fun login(email: String, password: String): Boolean {
        return registeredUsers.any {
            it.email.equals(email, ignoreCase = true) && it.password == password
        }
    }

}