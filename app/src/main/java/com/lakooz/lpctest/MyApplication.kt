package com.lakooz.lpctest

import android.app.Application
import android.content.Context
import com.lakooz.lpctest.database.AppDatabase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyApplication.applicationContext = applicationContext
        database = AppDatabase.getInstance(MyApplication.applicationContext)


    }

    companion object {
        lateinit  var applicationContext: Context
        lateinit var database: AppDatabase
        private set

    }
}