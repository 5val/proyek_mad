package com.example.proyek_mad.ui.listmodule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.data.repositories.MyRepository
import com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson
import com.example.proyek_mad.data.sources.remote.request.NextMateriRequest
import kotlinx.coroutines.launch

class ModuleViewModel(
    private val myRepository: MyRepository
):ViewModel() {
    private val _module = MutableLiveData<Result<Module>>()
    val module: LiveData<Result<Module>>
        get() = _module

    private val _toastku = MutableLiveData<String>()
    val toastku:LiveData<String>
        get() = _toastku

    private val _maxMateri = MutableLiveData<Int>()
    val maxMateri:LiveData<Int>
        get() = _maxMateri
    private val _minMateri = MutableLiveData<Int>()
    val minMateri:LiveData<Int>
        get() = _minMateri


    fun init() {
        viewModelScope.launch {
//            _module.value = MockDB.modules.filter { it.kelas_id == MockDB.selectedKelas && it.materi_id == MockDB.selectedMateri }
//            _maxMateri.value = MockDB.modules.filter { it.kelas_id == MockDB.selectedKelas }.maxOfOrNull { it.materi_id } ?: 0
            _module.value = myRepository.getMaterialById(MockDB.selectedMateri)
            var allMateri = myRepository.getMaterialsByCourse(MockDB.selectedKelas)
            _maxMateri.value = allMateri?.getOrNull()
                ?.maxOfOrNull { it.materi_id }
                ?: 0
            _minMateri.value = allMateri?.getOrNull()
                ?.minOfOrNull { it.materi_id }
                ?: 0
            if(!MockDB.onlineMode){
                val progress = myRepository.getCourseById(MockDB.selectedKelas, MockDB.currentUser.user_id)
                    ?.getOrNull()
                    ?.progress ?: 0
                val max = _maxMateri.value ?: 0
                val min = _minMateri.value ?: 0
                _maxMateri.value = ((max - min).toDouble() * progress / 100).toInt()+min
            }
//            _toastku.value = "${_maxMateri.value} sekarang:${MockDB.selectedMateri}"
        }
    }

    fun refresh() {
        viewModelScope.launch {
            myRepository.nextMateri(NextMateriRequest(MockDB.currentUser.user_id, MockDB.selectedKelas, MockDB.selectedMateri))
            _module.value = myRepository.getMaterialById(MockDB.selectedMateri)
        }
    }

    fun next() {
        MockDB.selectedMateri += 1
        refresh()
    }

    fun previous() {
        MockDB.selectedMateri -= 1
        refresh()
    }
}