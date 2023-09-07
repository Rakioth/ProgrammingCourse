package com.danielmunoz.android

import android.app.Application
import com.danielmunoz.db.AppDatabase

class RoomApplication : Application() {

    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        appDatabase = AppDatabase.getInstance(this)
    }

}