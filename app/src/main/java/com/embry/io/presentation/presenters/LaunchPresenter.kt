package com.embry.io.presentation.presenters

import com.embry.io.base.BasePresenter
import com.embry.io.domain.LauncherUsecases
import javax.inject.Inject

/**
 * Launch presenter of course.
 *
 * @author harshoverseer
 */
class LaunchPresenter @Inject constructor(val launcherUsecases: LauncherUsecases) : BasePresenter<LaunchPresenter.LauncherViewSurface>() {

    lateinit var mView: LauncherViewSurface

    override fun onStart(v: LauncherViewSurface) {
        mView = v
    }

    fun handleAddServerButtonClick() {
        mView.navigateToAddServerDialog()
    }

    fun handleAddDialogButtonClick(ip: String,
                                   username: String,
                                   password: String,
                                   name: String) {
        launcherUsecases.addMediaServer(ip, username, password, name)
    }

    override fun onStop(v: LauncherViewSurface) {
        mView = v
    }

    interface LauncherViewSurface {
        fun navigateToAddServerDialog()
    }
}