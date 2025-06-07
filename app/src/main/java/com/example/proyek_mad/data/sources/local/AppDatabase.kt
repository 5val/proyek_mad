package com.example.proyek_mad.data.sources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyek_mad.data.sources.local.Course.CourseDao
import com.example.proyek_mad.data.sources.local.Course.CourseEntity
import com.example.proyek_mad.data.sources.local.Module.ModuleDao
import com.example.proyek_mad.data.sources.local.Module.ModuleEntity
import com.example.proyek_mad.data.sources.local.User.UserDao
import com.example.proyek_mad.data.sources.local.User.UserEntity

@Database(
    entities = [UserEntity::class, ModuleEntity::class, CourseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun moduleDao():ModuleDao
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "project_mad"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}