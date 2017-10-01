package com.embry.io.presentation.view.activities;


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.embry.io.R
import com.embry.io.app.Casterly
import com.embry.io.injection.ActivityModule
import com.embry.io.injection.DaggerActivityComponent
import com.embry.io.presentation.view.presenters.MainPresenter
import com.embry.io.presentation.view.presenters.MainPresenter.MainViewSurface
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainViewSurface {

    @Inject
    lateinit var mPresenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        mPresenter.onStart(this)
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
