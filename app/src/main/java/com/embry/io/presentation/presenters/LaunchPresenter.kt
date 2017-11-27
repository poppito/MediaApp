package com.embry.io.presentation.presenters

import com.embry.io.base.BasePresenter
import com.embry.io.data.MediaServer
import com.embry.io.domain.LauncherUsecases
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Launch presenter of course.
 *
 * @author harshoverseer
 */
class LaunchPresenter @Inject constructor(val launcherUsecases: LauncherUsecases) : BasePresenter<LaunchPresenter.LauncherViewSurface>() {

    lateinit var mView: LauncherViewSurface

    var serverListDisposable: Disposable? = null
    var addServerDisposable : Disposable? = null

    override fun onStart(v: LauncherViewSurface) {
        mView = v

        serverListDisposable = launcherUsecases.getAllMediaServers()
                .subscribe(
                        {if (it.isNotEmpty()) {
                            mView.showAddServerButton(false)
                            mView.showServerList(true)
                            mView.showButtons(true)
                            mView.renderServerList(it)
                        }},
                        {}
                )
    }

    fun handleAddServerButtonClick() {
        mView.navigateToAddServerDialog()
    }

    fun handleServerAdd(ip: String,
                        username: String,
                        password: String,
                        domain: String,
                        name: String) {
        addServerDisposable = launcherUsecases.addMediaServer(ip, username, password, domain, name)
                .subscribe({mView.showServerAddedSnackbar() }
                        ,{})
    }

    fun handleServerItemClick(serverId : Int?) {
        if (serverId != null) {
            mView.navigateToMediaList(serverId)
        }
    }


    override fun onStop() {
        addServerDisposable?.dispose()
        serverListDisposable?.dispose()
    }

    interface LauncherViewSurface {
        fun showAddServerButton(show: Boolean)
        fun navigateToAddServerDialog()
        fun renderServerList(list : List<MediaServer>)
        fun showServerList(show: Boolean)
        fun showButtons(show: Boolean)
        fun showServerAddedSnackbar()
        fun navigateToMediaList(id : Int)
    }
}