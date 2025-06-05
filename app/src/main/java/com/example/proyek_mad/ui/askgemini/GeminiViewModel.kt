package com.example.proyek_mad.ui.askgemini

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.data.repositories.MyRepository
import kotlinx.coroutines.launch

class GeminiViewModel(
    private val myRepository: MyRepository
): ViewModel() {

}