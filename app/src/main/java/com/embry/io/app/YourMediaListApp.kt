package com.embry.io.app;

import android.app.Application
import android.arch.persistence.room.Room
import com.embry.io.data.MediaServerDb
import com.embry.io.injection.AppComponent
import com.embry.io.injection.AppModule
import com.embry.io.injection.DaggerAppComponent

class YourMediaList : Application() {

    lateinit var mAppComponent: AppComponent
    lateinit var mMediaServerDb : MediaServerDb

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        mAppComponent.inject(this)

        mMediaServerDb = Room.databaseBuilder(applicationContext, MediaServerDb::class.java, "MediaServerDb").build()
    }
}