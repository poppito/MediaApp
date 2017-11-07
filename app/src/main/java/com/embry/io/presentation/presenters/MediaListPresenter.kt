package com.embry.io.presentation.presenters


import com.embry.io.base.BasePresenter
import javax.inject.Inject

/**
 * Main presenter of course.
 *
 * @author harshoverseer
 */
class MediaListPresenter @Inject constructor() : BasePresenter<MediaListPresenter.MainViewSurface>() {

    lateinit var mView : MainViewSurface

    override fun onStart(v: MainViewSurface) {
        mView = v
    }

    override fun onStop(v: MainViewSurface) {
    }

    interface MainViewSurface {

    }
}
