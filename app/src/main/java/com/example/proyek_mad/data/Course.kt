package com.example.proyek_mad.data

import com.example.proyek_mad.data.sources.local.Course.CourseEntity
import com.example.proyek_mad.data.sources.remote.receive.CourseJson

data class Course (
    val kelas_id:Int,
    val nama_kelas:String,
    val deskripsi_kelas:String?,
    val prasyarat_kelas_id:Int?,
    val progress:Int,
    val attended:String
){
    fun toCourseEntity(userId:Int):CourseEntity{
        return CourseEntity(
            kelasId = this.kelas_id,
            userId = userId,
            namaKelas = this.nama_kelas,
            deskripsiKelas = this.deskripsi_kelas,
            prasyaratKelasId = this.prasyarat_kelas_id,
            progress = this.progress
        )
    }
}
