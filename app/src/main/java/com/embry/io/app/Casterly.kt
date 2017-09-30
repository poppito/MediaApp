package com.embry.io.app;

import android.app.Application
import com.embry.io.injection.AppComponent
import com.embry.io.injection.AppModule
import com.embry.io.injection.DaggerAppComponent

class Casterly : Application() {

    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        mAppComponent.inject(this)
    }
}
