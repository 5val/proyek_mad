package com.example.proyek_mad.data

import com.example.proyek_mad.data.sources.local.Module.ModuleEntity

data class Module (
    val materi_id:Int,
    val kelas_id:Int,
    val judul_materi:String,
    val konten_materi:String,
    val urutan_materi_dalam_kelas:Int,
    val deskripsi_singkat: String
){
    fun toModuleEntity():ModuleEntity{
        return ModuleEntity(
            materiId = this.materi_id,
            kelasId = this.kelas_id,
            judulMateri = this.judul_materi,
            kontenMateri = this.konten_materi,
            urutanMateriDalamKelas = this.urutan_materi_dalam_kelas,
            deskripsiSingkat = this.deskripsi_singkat
        )
    }
}