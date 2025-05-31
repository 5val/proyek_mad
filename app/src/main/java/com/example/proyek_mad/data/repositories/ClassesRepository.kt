package com.example.proyek_mad.data.repositories

import com.example.proyek_mad.data.Course

interface ClassesRepository {
    suspend fun getAllClasses():List<Course>
    suspend fun getOngoingClasses(userId:Int):List<Course>
    suspend fun getCompletedClasses(userId:Int):List<Course>
}