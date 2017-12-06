package com.embry.io.presentation.presenters

import com.embry.io.base.BasePresenter
import com.embry.io.data.MediaServer
import com.embry.io.domain.MediaServerUsecases
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Launch presenter of course.
 *
 * @author harshoverseer
 */
class LaunchPresenter @Inject constructor(val launcherUsecases: MediaServerUsecases) : BasePresenter<LaunchPresenter.LauncherViewSurface>() {

    lateinit var mView: LauncherViewSurface

    var serverListDisposable: Disposable? = null
    var addServerDisposable : Disposable? = null

    override fun onStart(v: LauncherViewSurface) {
        mView = v

        showServerListIfAvailable()
    }

    fun handleAddServerButtonClick() {
        mView.navigateToAddServerDialog()
    }

    fun handleServerAdd(ip: String,
                        username: String,
                        password: String,
                        domain: String,
                        name: String) {
        mView.showLoadingState(true)
        addServerDisposable = launcherUsecases.addMediaServer(ip, username, password, domain, name)
                .doAfterTerminate{
                    showServerListIfAvailable()
                }
                .subscribe({
                    mView.showLoadingState(false)
                    mView.showServerAddedSnackbar()
                }
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

    //region private

   private fun showServerListIfAvailable() {
       mView.showLoadingState(true)
       serverListDisposable = launcherUsecases.getAllMediaServers()
               .doOnTerminate {
                   mView.showLoadingState(false)
               }
               .subscribe(
                       {if (it.isNotEmpty()) {
                           mView.showAddServerButton(false)
                           mView.showServerList(true)
                           mView.showButtons(true)
                           mView.renderServerList(it)
                       }
                       else {
                           mView.showAddServerButton(true)
                           mView.showServerList(false)
                           mView.showButtons(false)
                       }},
                       {}
               )
    }

    //endregion

    interface LauncherViewSurface {
        fun showAddServerButton(show: Boolean)
        fun navigateToAddServerDialog()
        fun renderServerList(list : List<MediaServer>)
        fun showServerList(show: Boolean)
        fun showButtons(show: Boolean)
        fun showServerAddedSnackbar()
        fun navigateToMediaList(id : Int)
        fun showLoadingState(show: Boolean)
    }
}