package com.embry.io.presentation.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.embry.io.R
import com.embry.io.app.YourMediaList
import com.embry.io.injection.ActivityModule
import com.embry.io.injection.DaggerActivityComponent
import com.embry.io.presentation.presenters.LaunchPresenter
import kotlinx.android.synthetic.main.activity_launcher.*
import javax.inject.Inject

/**
 * Activity representing launch
 * screen.
 */
class LauncherActivity : AppCompatActivity(), LaunchPresenter.LauncherViewSurface {

    @Inject
    lateinit var mPresenter: LaunchPresenter

    private val path = "smb://"

    private val RESULT_ADD_SERVER = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        inject()
        mPresenter.onStart(this)
        btn_add_server.setOnClickListener { mPresenter.handleAddServerButtonClick() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESULT_ADD_SERVER) {
            if (resultCode == Activity.RESULT_OK) {

            }
        }
    }


    //region private

    fun inject() {
        val app = application as YourMediaList
        val activityModule = DaggerActivityComponent.builder()
                .appComponent(app.mAppComponent)
                .activityModule(ActivityModule(this))
                .build()

        activityModule.inject(this)
    }

    //endregion


    //region viewsurface

    override fun navigateToAddServerDialog() {
        startActivityForResult(Intent(this, AddServerActivity::class.java), RESULT_ADD_SERVER)
    }

    //endregion

    //region private

    //endregion

}