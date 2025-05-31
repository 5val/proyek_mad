package com.example.proyek_mad.data.sources.local.Module

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "materi"
)
data class ModuleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "materi_id")
    val materiId: Int = 0,

    @ColumnInfo(name = "kelas_id")
    val kelasId: Int,

    @ColumnInfo(name = "judul_materi")
    val judulMateri: String,

    @ColumnInfo(name = "deskripsi_singkat")
    val deskripsiSingkat: String,

    @ColumnInfo(name = "konten_materi")
    val kontenMateri: String?,

    @ColumnInfo(name = "tipe_materi")
    val tipeMateri: String = "text", // text, video, pdf, interactive

    @ColumnInfo(name = "urutan_materi_dalam_kelas")
    val urutanMateriDalamKelas: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Date = Date()
)