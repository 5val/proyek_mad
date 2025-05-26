package com.example.proyek_mad.ui.quiz

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
import kotlinx.coroutines.launch

class QuizViewModel: ViewModel() {
    private val _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz>
        get() = _quiz

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _options = MutableLiveData<List<Option>>()
    val options: LiveData<List<Option>>
        get() = _options

    var jmlQuestion = 0
    var selectedOption: Int = -1
    var nilaiUser = 0

    fun refresh() {
        startQuiz()
    }
    fun changeQuestion(){
        viewModelScope.launch {
            var urutan_sekarang = _question.value.urutan_soal + 1
            _question.value = MockDB.questions.find { it.kuis_id == _quiz.value.kuis_id && it.urutan_soal == urutan_sekarang }
            _options.value = MockDB.options.filter { it.soal_id == _question.value.soal_id }
        }
    }
    fun selectOption(option_id:Int){
        viewModelScope.launch {
            selectedOption = option_id
        }
    }
    fun submitAnswer(){
        viewModelScope.launch {
            var option = MockDB.options.find { it.pilihan_id == selectedOption }
            var isBenar = option!!.apakah_benar
            if(isBenar == 1) {
                nilaiUser += _question.value.poin_soal
            }
            MockDB.quizAnswers.add(QuizAnswer(MockDB.quizAnswers.size + 1, MockDB.quizAttempts[MockDB.quizAttempts.size-1].attempt_id, _question.value.soal_id, selectedOption, isBenar))
        }
    }
    fun finishQuiz(){
        viewModelScope.launch {
            var userAttempt = MockDB.quizAttempts[MockDB.quizAttempts.size-1]
            userAttempt.skor_diperoleh = nilaiUser
            if(nilaiUser >= _quiz.value.nilai_minimum_lulus) {
                userAttempt.status_lulus = 1
            } else {
                userAttempt.status_lulus = 0
            }
        }
    }
    fun startQuiz(){
        viewModelScope.launch {
            nilaiUser = 0
            _quiz.value = MockDB.quizzes.find { it.kelas_id == MockDB.selectedKelas }
            MockDB.quizAttempts.add(QuizAttempt(MockDB.quizAttempts.size + 1, 1, _quiz.value.kuis_id, 1, 0, 0))
            var questions = MockDB.questions.filter { it.kuis_id == _quiz.value.kuis_id }
            jmlQuestion = questions.size
            _question.value = MockDB.questions.find { it.kuis_id == _quiz.value.kuis_id && it.urutan_soal == 1 }
            _options.value = MockDB.options.filter { it.soal_id == _question.value.soal_id }
        }
    }
}