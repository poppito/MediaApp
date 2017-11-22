package com.embry.io.presentation.presenters

import android.util.Log
import com.embry.io.base.BasePresenter
import com.embry.io.domain.MediaServerRepo
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * add server presentation logic
 */
class AddServerPresenter @Inject constructor(val mediaServerRepo: MediaServerRepo) : BasePresenter<AddServerPresenter.ViewSurface>() {

    lateinit var mView: ViewSurface

    private var mVerifyDisposable: Disposable? = null

    override fun onStart(v: ViewSurface) {
        mView = v
    }

    override fun onStop() {
        mVerifyDisposable?.dispose()
    }


    fun handleAddServerButtonClick(ip: String, username: String, password: String, domain: String, name: String) {
        mView.showLoadingState(true)
        mVerifyDisposable = mediaServerRepo.verifyAddServer(ip, username, password, domain, name)
                .doOnTerminate {
                    mView.showLoadingState(false)
                }
                .subscribe(
                        {
                            mView.navigateToLauncherWithSuccess(ip, username, password, domain, name)
                        },
                        {
                            Log.v("TAG", it.message)
                        }
                )
    }

    interface ViewSurface {
        fun navigateToLauncherWithSuccess(ip: String, username: String, password: String, domain: String, name: String)
        fun showLoadingState(show: Boolean)
    }
}