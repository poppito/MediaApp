package com.embry.io.presentation.presenters


import com.embry.io.base.BasePresenter
import com.embry.io.data.MediaFile
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

    fun handleFileItemClick(file: MediaFile) {
        mView.showLoadingState(true)
        mView.showMediaList(false)
        if (file.isDirectory) {
            mServerConnectivityDisposable = mediaServerUsecases.getFilesInFilePath(file)
                    .doAfterTerminate { mView.showLoadingState(false) }
                    .subscribe(
                            {
                                mView.showMediaList(true)
                                mView.renderFileList(it, file.serverId)

                            },
                            {


                            }
                    )
        }
    }

    fun connectToServer(id: Int) {
        mView.showLoadingState(true)
        mServerConnectivityDisposable = mediaServerUsecases.connectToServer(id)
                .doAfterTerminate {
                    mView.showLoadingState(false)
                }
                .subscribe(
                        {
                            if (it.isNotEmpty()) {
                                mView.renderFileList(it, id)
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
        fun renderFileList(mediaList: Array<SmbFile>, id: Int)
        fun showLoadingState(show: Boolean)
        fun showMediaList(show: Boolean)
    }
}
