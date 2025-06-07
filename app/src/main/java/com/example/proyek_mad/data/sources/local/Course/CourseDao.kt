package com.example.proyek_mad.data.sources.local.Course

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyek_mad.data.sources.local.data_class.CourseWithProgress
import com.example.proyek_mad.data.sources.local.Module.ModuleEntity

@Dao
interface CourseDao {
    @Query("SELECT * FROM kelas WHERE user_id = :userId")
    suspend fun getCourseByUserId(userId: Int): List<CourseEntity>

    @Query("SELECT * FROM kelas WHERE kelas_id = :courseId")
    suspend fun getCourseById(courseId: Int): CourseEntity?

    @Insert
    suspend fun insertCourse(courseEntity: CourseEntity)

    @Delete
    suspend fun deleteCourse(courseEntity: CourseEntity)



//    // ===== BASIC CRUD OPERATIONS =====
//
//    @Query("SELECT * FROM kelas ORDER BY nama_kelas ASC")
//    suspend fun getAllPublishedCourses(): List<CourseEntity>
//
//    @Query("SELECT * FROM kelas WHERE kelas_id = :courseId")
//    suspend fun getCourseById(courseId: Int): CourseEntity?
//
//    @Insert
//    suspend fun insertCourse(course: CourseEntity)
//
//    @Update
//    suspend fun updateCourse(course: CourseEntity)
//
//    @Delete
//    suspend fun deleteCourse(course: CourseEntity)
//
//    // ===== COURSE WITH PROGRESS QUERIES =====
//
//    @Query("""
//        SELECT c.*,
//               COUNT(m.materi_id) as total_materials,
//               COUNT(CASE WHEN ump.status_selesai = 1 THEN 1 END) as completed_materials,
//               CASE
//                   WHEN COUNT(m.materi_id) = 0 THEN 0
//                   ELSE (COUNT(CASE WHEN ump.status_selesai = 1 THEN 1 END) * 100.0 / COUNT(m.materi_id))
//               END as progress_percentage,
//               uce.status_kelas as enrollment_status
//        FROM kelas c
//        LEFT JOIN materi m ON c.kelas_id = m.kelas_id
//        LEFT JOIN user_kelas_enrollment uce ON c.kelas_id = uce.kelas_id AND uce.user_id = :userId
//        LEFT JOIN user_materi_progress ump ON uce.enrollment_id = ump.enrollment_id AND m.materi_id = ump.materi_id
//        GROUP BY c.kelas_id
//        ORDER BY c.nama_kelas ASC
//    """)
//    suspend fun getCoursesWithProgress(userId: Int): List<CourseWithProgress>
//
//    @Query("""
//        SELECT c.*,
//               COUNT(m.materi_id) as total_materials,
//               COUNT(CASE WHEN ump.status_selesai = 1 THEN 1 END) as completed_materials,
//               CASE
//                   WHEN COUNT(m.materi_id) = 0 THEN 0
//                   ELSE (COUNT(CASE WHEN ump.status_selesai = 1 THEN 1 END) * 100.0 / COUNT(m.materi_id))
//               END as progress_percentage,
//               uce.status_kelas as enrollment_status
//        FROM kelas c
//        LEFT JOIN materi m ON c.kelas_id = m.kelas_id
//        LEFT JOIN user_kelas_enrollment uce ON c.kelas_id = uce.kelas_id AND uce.user_id = :userId
//        LEFT JOIN user_materi_progress ump ON uce.enrollment_id = ump.enrollment_id AND m.materi_id = ump.materi_id
//        WHERE c.kelas_id = :courseId
//        GROUP BY c.kelas_id
//    """)
//    suspend fun getCourseWithProgress(userId: Int, courseId: Int): CourseWithProgress?
//
//    @Query("""
//        SELECT c.* FROM kelas c
//        INNER JOIN user_kelas_enrollment uce ON c.kelas_id = uce.kelas_id
//        WHERE uce.user_id = :userId AND uce.status_kelas = :status
//    """)
//    suspend fun getUserCoursesByStatus(userId: Int, status: String): List<CourseEntity>
//
//    // ===== MATERIALS QUERIES =====
//
//    @Query("SELECT * FROM materi WHERE kelas_id = :courseId ORDER BY urutan_materi_dalam_kelas ASC")
//    suspend fun getMaterialsByCourse(courseId: Int): List<ModuleEntity>
//
//    @Query("SELECT * FROM materi WHERE materi_id = :materialId")
//    suspend fun getMaterialById(materialId: Int): ModuleEntity?
}
