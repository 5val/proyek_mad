package com.example.proyek_mad.data

import com.example.proyek_mad.data.sources.remote.receive.CourseJson

data class Course (
    val kelas_id:Int,
    val nama_kelas:String,
    val deskripsi_kelas:String?,
    val prasyarat_kelas_id:Int?,
)
