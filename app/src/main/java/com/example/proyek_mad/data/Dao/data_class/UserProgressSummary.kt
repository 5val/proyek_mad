package com.example.proyek_mad.data.Dao.data_class

import androidx.room.ColumnInfo

data class UserProgressSummary(
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "total_enrolled_courses") val totalEnrolledCourses: Int,
    @ColumnInfo(name = "completed_courses") val completedCourses: Int,
    @ColumnInfo(name = "in_progress_courses") val inProgressCourses: Int,
    @ColumnInfo(name = "total_materials_completed") val totalMaterialsCompleted: Int,
    @ColumnInfo(name = "average_score") val averageScore: Float?
)