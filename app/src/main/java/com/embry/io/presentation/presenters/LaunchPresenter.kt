package com.embry.io.presentation.presenters

import com.embry.io.base.BasePresenter
import javax.inject.Inject

/**
 * Launch presenter of course.
 *
 * @author harshoverseer
 */
class LaunchPresenter @Inject constructor() : BasePresenter<LaunchPresenter.LauncherViewSurface>() {

    lateinit var mView : LauncherViewSurface

    override fun onStart(v: LauncherViewSurface) {
        mView = v
    }

    fun handleAddServerButtonClick() {
        mView.showAddServerDialog()
    }

    override fun onStop(v: LauncherViewSurface) {
        mView = v
    }

    interface LauncherViewSurface {
        fun showAddServerDialog()
    }
}