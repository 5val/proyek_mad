package com.example.proyek_mad.ui.detailmateri

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import kotlinx.coroutines.launch

class CourseDetailViewModel:ViewModel() {
    private val _materi = MutableLiveData<List<Module>>()
    val materi: LiveData<List<Module>>
        get() = _materi

    fun refresh() {
        viewModelScope.launch {
            _materi.value = MockDB.modules.filter { it.kelas_id == MockDB.selectedKelas }
        }
    }


}