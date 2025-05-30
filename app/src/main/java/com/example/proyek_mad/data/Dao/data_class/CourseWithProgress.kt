package com.example.proyek_mad.data.Dao.data_class

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.example.proyek_mad.data.Dao.entity.CourseEntity

data class CourseWithProgress(
    @Embedded val course: CourseEntity,
    @ColumnInfo(name = "total_materials") val totalMaterials: Int,
    @ColumnInfo(name = "completed_materials") val completedMaterials: Int,
    @ColumnInfo(name = "progress_percentage") val progressPercentage: Float,
    @ColumnInfo(name = "enrollment_status") val enrollmentStatus: String?
)