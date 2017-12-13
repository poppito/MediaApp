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

    private var previousPath = ""

    private var currentServerId : Int = 0

    override fun onStart(v: MainViewSurface) {
        mView = v
        handleBackButtonVisibility()
    }

    fun handleFileItemClick(file: MediaFile) {
        previousPath = previousPath + ":" + file.name
        handleBackButtonVisibility()
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
        currentServerId = id
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

    fun handleBackButtonVisibility() {
        if (previousPath.equals("")) {
            mView.showBackButton(false)
        } else {
            mView.showBackButton(true)
        }
    }

    fun handleBackButtonClick() {
        val path = previousPath.split(":")
        if (path.size > 1) {
            val newPath = path.get(path.size - 1)

        } else {
            mView.showBackButton(false)
        }
    }

    override fun onStop() {
    }

    interface MainViewSurface {
        fun renderFileList(mediaList: Array<SmbFile>, id: Int)
        fun showLoadingState(show: Boolean)
        fun showMediaList(show: Boolean)
        fun showBackButton(show: Boolean)
    }
}
