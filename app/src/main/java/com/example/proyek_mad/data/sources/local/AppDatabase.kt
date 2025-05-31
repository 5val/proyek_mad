package com.example.proyek_mad.data.sources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyek_mad.data.sources.local.Course.CourseDao
import com.example.proyek_mad.data.sources.local.Course.CourseEntity
import com.example.proyek_mad.data.sources.local.Course.UserCourseEnrollmentEntity
import com.example.proyek_mad.data.sources.local.Module.ModuleEntity
import com.example.proyek_mad.data.sources.local.Module.ProgressDao
import com.example.proyek_mad.data.sources.local.Module.UserModuleProgressEntity
import com.example.proyek_mad.data.sources.local.User.UserEntity

@Database(entities = [UserEntity::class, ModuleEntity::class, CourseEntity::class, UserCourseEnrollmentEntity::class, UserModuleProgressEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun progressDao():ProgressDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "mdpinf20242m10"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}