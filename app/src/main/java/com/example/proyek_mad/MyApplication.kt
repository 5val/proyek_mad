package com.example.proyek_mad

import android.app.Application
import com.example.proyek_mad.data.repositories.MyDefaultRepository
import com.example.proyek_mad.data.repositories.MyRepository
import com.example.proyek_mad.data.sources.local.AppDatabase
import com.example.proyek_mad.data.sources.local.RoomDataSource
//import com.example.proyek_mad.data.sources.local.AppDatabase
//import com.example.proyek_mad.data.sources.local.RoomDataSource
import com.example.proyek_mad.data.sources.remote.RetrofitDataSource
import com.example.proyek_mad.data.sources.remote.WebService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MyApplication:Application() {
    lateinit var myRepository: MyRepository
    override fun onCreate() {
        super.onCreate()
//        todoRepository = TodoDefaultRepository(
//            RoomDataSource(AppDatabase.getInstance(baseContext))
//        )
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder().addConverterFactory(
            MoshiConverterFactory.create(moshi)
        ).baseUrl("http://vpscbt2.mysites.my.id/api/").build()
        val retrofitService = retrofit.create(WebService::class.java)
        myRepository = MyDefaultRepository(
            RoomDataSource(AppDatabase.getInstance(baseContext)),
            RetrofitDataSource(retrofitService)
        )
    }
}