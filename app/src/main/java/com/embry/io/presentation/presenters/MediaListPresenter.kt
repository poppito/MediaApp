package com.embry.io.presentation.presenters


import com.embry.io.base.BasePresenter
import com.embry.io.domain.MediaServerUsecases
import io.reactivex.disposables.Disposable
import jcifs.smb.SmbFile
import javax.inject.Inject

/**
 * Main presenter of course.
 *
 * @author harshoverseer
 */
class MediaListPresenter @Inject constructor(val mediaServerUsecases: MediaServerUsecases) :
        BasePresenter<MediaListPresenter.MainViewSurface>() {

    lateinit var mView: MainViewSurface

    var mServerConnectivityDisposable: Disposable? = null

    override fun onStart(v: MainViewSurface) {
        mView = v
    }

    fun connectToServer(id: Int?) {
        mView.showLoadingState(true)
        mServerConnectivityDisposable = mediaServerUsecases.connectToServer(id)
                .doAfterTerminate {
                    mView.showLoadingState(false)
                }
                .subscribe(
                        {
                            if (it.isNotEmpty()) {
                                mView.renderFileList(it)
                                mView.showMediaList(true)
                            }
                        },
                        {

                        }
                )
    }

    override fun onStop() {
    }

    interface MainViewSurface {
        fun renderFileList(mediaList: Array<SmbFile>)
        fun showLoadingState(show: Boolean)
        fun showMediaList(show: Boolean)
    }
}
