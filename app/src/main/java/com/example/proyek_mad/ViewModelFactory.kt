package com.example.proyek_mad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.proyek_mad.ui.detailkelas.CourseDetailViewModel
import com.example.proyek_mad.ui.home.HomeViewModel
import com.example.proyek_mad.ui.listkelas.CoursesViewModel
import com.example.proyek_mad.ui.listmodule.ModuleViewModel
import com.example.proyek_mad.ui.loginregister.LoginRegisterViewModel
import com.example.proyek_mad.ui.offlinecourse.OfflineCoursesViewModel
import com.example.proyek_mad.ui.profile.ProfileViewModel
import com.example.proyek_mad.ui.quiz.QuizViewModel

val MyViewModelFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
        with(modelClass) {
            val application = checkNotNull(extras[APPLICATION_KEY]) as MyApplication
            val myRepository = application.myRepository
            when {
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(myRepository)
                isAssignableFrom(CourseDetailViewModel::class.java) ->
                    CourseDetailViewModel(myRepository)
                isAssignableFrom(CoursesViewModel::class.java) ->
                    CoursesViewModel(myRepository)
                isAssignableFrom(ModuleViewModel::class.java) ->
                    ModuleViewModel(myRepository)
                isAssignableFrom(OfflineCoursesViewModel::class.java) ->
                    OfflineCoursesViewModel(myRepository)
                isAssignableFrom(QuizViewModel::class.java) ->
                    QuizViewModel(myRepository)
                isAssignableFrom(ProfileViewModel::class.java) ->
                    ProfileViewModel(myRepository)
                isAssignableFrom(LoginRegisterViewModel::class.java) ->
                    LoginRegisterViewModel(myRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}