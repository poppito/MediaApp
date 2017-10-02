package com.embry.io.presentation.presenters;


import com.embry.io.base.BasePresenter
import javax.inject.Inject

/**
 * Main presenter of course.
 *
 * @author harshoverseer
 */
class MainPresenter @Inject constructor() : BasePresenter<MainPresenter.MainViewSurface>() {
    override fun onStart(v: MainViewSurface) {
    }

    override fun onStop(v: MainViewSurface) {
    }

    interface MainViewSurface
}
