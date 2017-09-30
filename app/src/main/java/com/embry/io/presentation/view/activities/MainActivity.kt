package com.embry.io.presentation.view.activities;


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.embry.io.R
import com.embry.io.app.Casterly
import com.embry.io.injection.ActivityModule
import com.embry.io.injection.DaggerActivityComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
    }

    private fun inject() {
        val app = application as Casterly
        DaggerActivityComponent.builder()
                .appComponent(app.mAppComponent)
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
    }
}
