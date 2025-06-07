package com.example.proyek_mad.ui.askgemini

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyek_mad.data.Chat
import com.example.proyek_mad.data.MockDB
import com.example.proyek_mad.data.Module
import com.example.proyek_mad.data.repositories.MyRepository
import com.example.proyek_mad.data.sources.remote.request.GeminiRequest
import kotlinx.coroutines.launch

class GeminiViewModel(
    private val myRepository: MyRepository
): ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message
    private val _arrChat = MutableLiveData<List<Chat>>()
    val arrChat: LiveData<List<Chat>>
        get() = _arrChat

    fun refresh(){
        _arrChat.value = MockDB.geminiChats
    }
    fun sendMessage(message:String){
        viewModelScope.launch {
            MockDB.geminiChats += Chat(
                MockDB.geminiChats.last().chat_id+1,
                2,
                message
            )
            refresh()
            var response = myRepository.askGemini(GeminiRequest(message))
            response.onSuccess {it->
                MockDB.geminiChats += Chat(
                    MockDB.geminiChats.last().chat_id+1,
                    1,
                    it.msg
                )
                refresh()
            }.onFailure { err->
                _message.value = err.message
            }
        }
    }

}