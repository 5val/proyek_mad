package com.example.proyek_mad.data

data class Chat (
    val chat_id:Int,
    val from:Int, // 1: gemini, 2: user
    val message:String
)