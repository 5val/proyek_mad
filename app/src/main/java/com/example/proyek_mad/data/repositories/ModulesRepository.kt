package com.example.proyek_mad.data.repositories

import com.example.proyek_mad.data.Module
import com.example.proyek_mad.data.Quiz

interface ModulesRepository {
    suspend fun getAllModule(kelasId:Int):List<Module>
    suspend fun getModule(materiId:Int):Module
    suspend fun getQuiz(kelasId:Int):Quiz
}