package com.example.proyek_mad.data.sources.local.Module

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "user_materi_progress"
)
data class UserModuleProgressEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "progress_id")
    val progressId: Int = 0,

    @ColumnInfo(name = "enrollment_id")
    val enrollmentId: Int,

    @ColumnInfo(name = "materi_id")
    val materiId: Int,

    @ColumnInfo(name = "status_selesai")
    val statusSelesai: Boolean = false,

    @ColumnInfo(name = "tanggal_diselesaikan")
    val tanggalDiselesaikan: Date?,

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Date = Date()
)