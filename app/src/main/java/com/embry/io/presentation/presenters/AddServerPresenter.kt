package com.embry.io.presentation.presenters

import com.embry.io.base.BasePresenter
import javax.inject.Inject

/**
 * add server presentation logic
 */
class AddServerPresenter @Inject constructor() : BasePresenter<AddServerPresenter.ViewSurface>() {

    lateinit var mView : ViewSurface

    override fun onStart(v: ViewSurface) {
        mView = v
    }

    override fun onStop(v: ViewSurface) {
        mView = v
    }


    fun handleAddServerButtonClick() {
        mView.addServer()
    }

    interface ViewSurface{
        fun addServer()
    }
}