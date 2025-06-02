package com.example.proyek_mad.data.sources.local.Module

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyek_mad.data.sources.local.data_class.UserProgressSummary
import com.example.proyek_mad.data.sources.local.Course.UserCourseEnrollmentEntity
import java.util.Date

@Dao
interface ProgressDao {

    // ===== ENROLLMENT OPERATIONS =====

    @Insert
    suspend fun enrollUserToCourse(enrollment: UserCourseEnrollmentEntity)

    @Query("SELECT * FROM user_kelas_enrollment WHERE user_id = :userId AND kelas_id = :courseId")
    suspend fun getUserEnrollment(userId: Int, courseId: Int): UserCourseEnrollmentEntity?

    @Query("SELECT * FROM user_kelas_enrollment WHERE user_id = :userId")
    suspend fun getUserEnrollments(userId: Int): List<UserCourseEnrollmentEntity>

    @Update
    suspend fun updateEnrollment(enrollment: UserCourseEnrollmentEntity)

    // ===== PROGRESS OPERATIONS =====

    @Insert
    suspend fun insertOrUpdateProgress(progress: UserModuleProgressEntity)

    @Query("SELECT * FROM user_materi_progress WHERE enrollment_id = :enrollmentId")
    suspend fun getProgressByEnrollment(enrollmentId: Int): List<UserModuleProgressEntity>

    @Query("""
        SELECT ump.* FROM user_materi_progress ump
        INNER JOIN user_kelas_enrollment uce ON ump.enrollment_id = uce.enrollment_id
        WHERE uce.user_id = :userId AND uce.kelas_id = :courseId
    """)
    suspend fun getUserCourseProgress(userId: Int, courseId: Int): List<UserModuleProgressEntity>

    @Query("""
        UPDATE user_materi_progress 
        SET status_selesai = :completed
        WHERE enrollment_id = :enrollmentId AND materi_id = :materialId
    """)
    suspend fun updateMaterialProgress(enrollmentId: Int, materialId: Int, completed: Boolean)

    // ===== PROGRESS SUMMARY =====

    @Query("""
        SELECT 
            :userId as user_id,
            COUNT(DISTINCT uce.kelas_id) as total_enrolled_courses,
            COUNT(DISTINCT CASE WHEN uce.status_kelas = 'selesai' THEN uce.kelas_id END) as completed_courses,
            COUNT(DISTINCT CASE WHEN uce.status_kelas = 'sedang_dipelajari' THEN uce.kelas_id END) as in_progress_courses,
            COUNT(CASE WHEN ump.status_selesai = 1 THEN 1 END) as total_materials_completed,
            AVG(uce.skor_akhir_kelas) as average_score
        FROM user_kelas_enrollment uce
        LEFT JOIN user_materi_progress ump ON uce.enrollment_id = ump.enrollment_id
        WHERE uce.user_id = :userId
    """)
    suspend fun getUserProgressSummary(userId: Int): UserProgressSummary?

    @Query("""
        SELECT 
            COUNT(m.materi_id) as total_materials,
            COUNT(CASE WHEN ump.status_selesai = 1 THEN 1 END) as completed_materials
        FROM materi m
        LEFT JOIN user_kelas_enrollment uce ON m.kelas_id = uce.kelas_id AND uce.user_id = :userId
        LEFT JOIN user_materi_progress ump ON uce.enrollment_id = ump.enrollment_id AND m.materi_id = ump.materi_id
        WHERE m.kelas_id = :courseId
    """)
    suspend fun getCourseProgressCount(userId: Int, courseId: Int): Map<String, Int>

    // ===== UTILITY METHODS =====

    @Query("""
        UPDATE user_kelas_enrollment 
        SET materi_terakhir_diakses_id = :materialId
        WHERE enrollment_id = :enrollmentId
    """)
    suspend fun updateLastAccessedMaterial(enrollmentId: Int, materialId: Int)

    @Query("""
        SELECT COUNT(*) FROM user_materi_progress ump
        INNER JOIN user_kelas_enrollment uce ON ump.enrollment_id = uce.enrollment_id
        WHERE uce.user_id = :userId AND uce.kelas_id = :courseId AND ump.status_selesai = 1
    """)
    suspend fun getCompletedMaterialsCount(userId: Int, courseId: Int): Int

    @Query("""
        SELECT COUNT(*) FROM materi
        WHERE kelas_id = :courseId
    """)
    suspend fun getTotalMaterialsCount(courseId: Int): Int
}