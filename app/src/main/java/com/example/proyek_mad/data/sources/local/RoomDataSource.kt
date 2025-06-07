package com.example.proyek_mad.data.sources.local

import com.example.proyek_mad.data.sources.local.Course.CourseEntity
import com.example.proyek_mad.data.sources.local.Module.ModuleEntity
import com.example.proyek_mad.data.sources.local.User.UserEntity

class RoomDataSource(private val db: AppDatabase) : LocalDataSource {

    // --- Course Methods ---

    override suspend fun getCoursesByUserId(userId: Int): List<CourseEntity> {
        return db.courseDao().getCourseByUserId(userId)
    }

    override suspend fun getCourseById(courseId: Int, userId: Int): CourseEntity? {
        return db.courseDao().getCourseById(courseId, userId)
    }

    override suspend fun insertCourse(courseEntity: CourseEntity): CourseEntity {
        db.courseDao().insertCourse(courseEntity)
        return courseEntity // Mengembalikan objek setelah dimasukkan
    }

    override suspend fun deleteCourse(courseEntity: CourseEntity): CourseEntity {
        db.courseDao().deleteCourse(courseEntity)
        return courseEntity // Mengembalikan objek yang dihapus
    }

    // --- Module Methods ---

    override suspend fun getModulesByCourseId(courseId: Int): List<ModuleEntity> {
        return db.moduleDao().getModuleByCourseId(courseId)
    }

    override suspend fun getModule(moduleId: Int): ModuleEntity? {
        return db.moduleDao().getModuleById(moduleId)
    }

    override suspend fun insertModule(moduleEntity: ModuleEntity): ModuleEntity {
        // Asumsi nama metode di DAO adalah 'insertCourse' sesuai definisi awal Anda
        db.moduleDao().insertModule(moduleEntity)
        return moduleEntity // Mengembalikan objek setelah dimasukkan
    }

    override suspend fun deleteModule(moduleEntity: ModuleEntity): ModuleEntity {
        // Asumsi nama metode di DAO adalah 'deleteCourse' sesuai definisi awal Anda
        db.moduleDao().deleteModule(moduleEntity)
        return moduleEntity // Mengembalikan objek yang dihapus
    }

    // --- User Methods ---

    override suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity? {
        return try {
            db.userDao().getUserByEmailAndPassword(email, password)
        } catch (e: Exception) {
            // Menangani kasus jika DAO mengembalikan non-nullable dan query tidak menemukan hasil
            null
        }
    }

    override suspend fun insertUser(userEntity: UserEntity): UserEntity {
        db.userDao().insertUser(userEntity)
        return userEntity // Mengembalikan objek setelah dimasukkan
    }

    override suspend fun updateUser(userEntity: UserEntity): UserEntity {
        db.userDao().updateUser(userEntity) // Memanggil metode update dari DAO
        return userEntity // Mengembalikan objek yang telah diperbarui
    }
}