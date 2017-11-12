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

    override fun onStop(v: ViewSurface) {
        mView = v
    }


    fun handleAddServerButtonClick(ip: String, username: String, password: String, name: String) {
        mVerifyDisposable = mediaServerRepo.addMediaServer(name, ip, username, password)
                .subscribe(
                        { it.forEach { Log.v("TAG", it.name.toString()) }

                        },
                        {
                            Log.v("TAG", it.message)
                        }
                )
    }

    interface ViewSurface {
    }
}