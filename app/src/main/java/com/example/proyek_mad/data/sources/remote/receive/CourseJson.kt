package com.example.proyek_mad.data.sources.remote.receive

import com.example.proyek_mad.data.Course
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseJson(
    val kelas_id: Int,
    val nama_kelas: String,
    val deskripsi_kelas: String?,
    val prasyarat_kelas_id: Int?,
    val created_at: String,
    val updated_at: String
){
    fun toCourse(): Course {
        return Course(
            kelas_id = this.kelas_id,
            nama_kelas = this.nama_kelas,
            deskripsi_kelas = this.deskripsi_kelas,
            prasyarat_kelas_id = this.prasyarat_kelas_id
        )
    }
}