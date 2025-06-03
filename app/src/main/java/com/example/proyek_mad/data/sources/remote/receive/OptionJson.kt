package com.example.proyek_mad.data.sources.remote.receive

import com.example.proyek_mad.data.Option
import com.example.proyek_mad.data.Question
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OptionJson(
    val pilihan_id:Int,
    val soal_id:Int,
    val teks_pilihan_jawaban:String,
    val apakah_benar:Int,
    val created_at: String,
    val updated_at: String
){
    fun toOption(): Option {
        return Option(
            pilihan_id = this.pilihan_id,
            soal_id = this.soal_id,
            teks_pilihan_jawaban = this.teks_pilihan_jawaban,
            apakah_benar = this.apakah_benar
        )
    }
}