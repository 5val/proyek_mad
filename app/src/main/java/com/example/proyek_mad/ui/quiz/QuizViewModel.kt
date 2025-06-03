package com.example.proyek_mad.ui.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Course
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Option
import com.example.proyek_mad.data.Question
import com.example.proyek_mad.data.Quiz
import com.example.proyek_mad.data.QuizAnswer
import com.example.proyek_mad.data.QuizAttempt
import com.example.proyek_mad.data.repositories.MyRepository
import com.example.proyek_mad.data.sources.remote.receive.EnrollmentJson
import com.example.proyek_mad.data.sources.remote.request.CreateQuizAttemptRequest
import com.example.proyek_mad.data.sources.remote.request.QuizAnswerRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizViewModel(
    private val myRepository:MyRepository
): ViewModel() {
    private val _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz>
        get() = _quiz

    private val _quizQuestions = MutableLiveData<List<Question>>()
    val quizQuestions: LiveData<List<Question>>
        get() = _quizQuestions

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _options = MutableLiveData<List<Option>>()
    val options: LiveData<List<Option>>
        get() = _options

    private val _enrollment = MutableLiveData<EnrollmentJson>()
    val enrollment: LiveData<EnrollmentJson>
        get() = _enrollment

    private val _sisaWaktu = MutableLiveData<String>()
    val sisaWaktu: LiveData<String>
        get() = _sisaWaktu


    var jmlQuestion = 0
    var selectedOption: Int = -1
    var nilaiUser = 0
    var jawabanBenar = 0
    var lastAttempt: QuizAttempt? = null

    fun refresh() {
        viewModelScope.launch {
        getQuiz()
        getQuizQuestions()
        getFirstQuestion()
        startQuiz()
        }
    }
    fun changeQuestion(){
        viewModelScope.launch {
            var urutan_sekarang = _question.value.urutan_soal + 1
            _question.value = quizQuestions.value.find { it.kuis_id == _quiz.value.kuis_id && it.urutan_soal == urutan_sekarang }
//            _options.value = MockDB.options.filter { it.soal_id == _question.value.soal_id }
            _options.value = myRepository.getPilihanSoal(_question.value?.soal_id?:0)
                ?.getOrNull()?: listOf()
        }
    }
    fun selectOption(option_id:Int){
        viewModelScope.launch {
            selectedOption = option_id
        }
    }
    fun submitAnswer(){
        viewModelScope.launch {
            var option = _options.value.find { it.pilihan_id == selectedOption }
            var isBenar = option!!.apakah_benar

            if(isBenar == 1) {
                nilaiUser += _question.value.poin_soal
                jawabanBenar++
            }
//            MockDB.quizAnswers.add(QuizAnswer(MockDB.quizAnswers.size + 1, MockDB.quizAttempts[MockDB.quizAttempts.size-1].attempt_id, _question.value.soal_id, selectedOption, isBenar))
            myRepository.jawabSoal(QuizAnswerRequest(lastAttempt?.attempt_id ?: 0, selectedOption, isBenar), _question.value.soal_id)
        }
    }
    fun finishQuiz(){
        viewModelScope.launch {
//            var userAttempt = MockDB.quizAttempts[MockDB.quizAttempts.size-1]
            var userAttempt = myRepository.getKuisAttemptTerakhir().getOrNull()
            userAttempt?.skor_diperoleh = nilaiUser
            if(nilaiUser >= _quiz.value.nilai_minimum_lulus) {
                userAttempt?.status_lulus = 1
            } else {
                userAttempt?.status_lulus = 0
            }
        }
    }
    suspend fun getQuiz() {
            _quiz.value = myRepository.getKuisKelas(MockDB.selectedKelas).getOrNull()?:null
        _enrollment.value = myRepository.getEnrollment(MockDB.currentUser.user_id, MockDB.selectedKelas).getOrNull()?:null

    }
    suspend fun getQuizQuestions() {
            _quizQuestions.value = myRepository.getAllSoalKuis(_quiz.value?.kuis_id?:0).getOrNull()?: listOf()

    }
    suspend fun getFirstQuestion() {
            _question.value = _quizQuestions.value.find { it.kuis_id == _quiz.value.kuis_id && it.urutan_soal == 1 }

    }
    suspend fun getLastAttempt() {
            lastAttempt = myRepository.getKuisAttemptTerakhir().getOrNull()

    }
    suspend fun startQuiz(){
            _sisaWaktu.value = ""
            nilaiUser = 0
            jawabanBenar = 0
//            _quiz.value = MockDB.quizzes.find { it.kelas_id == MockDB.selectedKelas }
//            MockDB.quizAttempts.add(QuizAttempt(MockDB.quizAttempts.size + 1, 1, _quiz.value.kuis_id, 1, 0, 0))
//            var enrollment = myRepository.getEnrollment(MockDB.currentUser.user_id, MockDB.selectedKelas)
            Log.d("debug", _enrollment.value?.enrollment_id.toString())
            myRepository.startKuis(CreateQuizAttemptRequest(MockDB.currentUser.user_id, _enrollment.value?.enrollment_id?:0), _quiz.value?.kuis_id?:0)
//            _quizQuestions.value = MockDB.questions.filter { it.kuis_id == _quiz.value.kuis_id }

            jmlQuestion = _quizQuestions.value.size

//            _options.value = MockDB.options.filter { it.soal_id == _question.value.soal_id }
            _options.value = myRepository.getPilihanSoal(_question.value.soal_id)?.getOrNull()?: listOf()
            startTimer(Integer.valueOf(_quiz.value.batas_waktu_pengerjaan_menit))

    }
    fun startTimer(batas_waktu: Int) {
        val detik = batas_waktu * 60
        viewModelScope.launch {
            var sisaDetik = detik
            while (sisaDetik >= 0) {
                val min = sisaDetik / 60
                val sec = sisaDetik % 60
                _sisaWaktu.value = String.format("%02d:%02d", min, sec)
                delay(1000L)
                sisaDetik--
                if (sisaDetik < 0) {
                    _sisaWaktu.value = "00:00"
                    finishQuiz()
                }
            }
        }
    }

}