package com.techkingsley.agromall.base

import android.app.Application
import com.facebook.stetho.Stetho
import com.techkingsley.agromall.BuildConfig

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}