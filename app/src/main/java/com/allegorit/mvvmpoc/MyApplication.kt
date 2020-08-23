package com.allegorit.mvvmpoc

import android.app.Application
import com.allegorit.mvvmpoc.di.museumModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(museumModel)
        }
    }
}
