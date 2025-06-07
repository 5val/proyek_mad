package com.example.proyek_mad.data

object MockDB {
    var selectedMateri:Int = 1
    var selectedKelas:Int = 101
    var onlineMode = true

    var currentUser:User = User(
        1,
        "valen",
        "valen@gmail.com",
        "valen123",
        "Valensia Clarins"
    )

    var courses:List<Course> = listOf(

    )

    var geminiChats:List<Chat> = listOf(
        Chat(1, 1, "Hello, how can I help you?"),
    )
}