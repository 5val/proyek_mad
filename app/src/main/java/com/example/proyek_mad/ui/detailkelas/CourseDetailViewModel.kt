package com.example.proyek_mad.ui.detailkelas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import kotlinx.coroutines.launch

class CourseDetailViewModel:ViewModel() {
    private val _materi = MutableLiveData<List<Module>>()
    val materi: LiveData<List<Module>>
        get() = _materi

    var nilaiTerbaik = 0

    fun refresh() {
        viewModelScope.launch {
            _materi.value = MockDB.modules.filter { it.kelas_id == MockDB.selectedKelas }
            var kuisKelas = MockDB.quizzes.find { it.kelas_id == MockDB.selectedKelas }
            var kuisAttempts = MockDB.quizAttempts.filter { it.kuis_id == kuisKelas?.kuis_id }.sortedByDescending {
                it.skor_diperoleh
            }
            if(kuisAttempts.size > 0) {
                nilaiTerbaik = kuisAttempts[0].skor_diperoleh
            }
        }
    }


}