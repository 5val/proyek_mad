package com.example.proyek_mad.data.sources.local.Module

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.proyek_mad.data.sources.local.Course.CourseEntity

@Dao
interface ModuleDao {
    @Query("SELECT * FROM materi WHERE kelas_id = :courseId")
    suspend fun getModuleByCourseId(courseId: Int): List<ModuleEntity>

    @Insert
    suspend fun insertCourse(moduleEntity: ModuleEntity)

    @Delete
    suspend fun deleteCourse(moduleEntity: ModuleEntity)
}