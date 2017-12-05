package com.embry.io.presentation.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import com.embry.io.R
import com.embry.io.app.YourMediaList
import com.embry.io.data.MediaServer
import com.embry.io.injection.ActivityModule
import com.embry.io.injection.DaggerActivityComponent
import com.embry.io.presentation.presenters.LaunchPresenter
import kotlinx.android.synthetic.main.activity_launcher.*
import javax.inject.Inject

/**
 * Activity representing launch
 * screen.
 */
class LauncherActivity : AppCompatActivity(), LaunchPresenter.LauncherViewSurface {

    @Inject
    lateinit var mPresenter: LaunchPresenter

    private val requestAddServer = 9001

    private var serverAdapter: ArrayAdapter<MediaServer>? = null

    companion object {
        val serverId = "ServerId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        inject()
        mPresenter.onStart(this)
        btn_add_server.setOnClickListener { mPresenter.handleAddServerButtonClick() }
        btn_add_a_server.setOnClickListener { mPresenter.handleAddServerButtonClick() }
        list_servers.setOnItemClickListener { parent, view, position, id ->
            mPresenter.handleServerItemClick(serverAdapter?.getItem(position)?.id)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == requestAddServer) {
            if (resultCode == Activity.RESULT_OK) {
                var ip = ""
                var username = ""
                var password = ""
                var name = ""
                var domain = ""
                if (data?.getStringExtra(AddServerActivity.RESULT_IP) != null) {
                    ip = data.getStringExtra(AddServerActivity.RESULT_IP)
                }
                if (data?.getStringExtra(AddServerActivity.RESULT_USERNAME) != null) {
                    username = data.getStringExtra(AddServerActivity.RESULT_USERNAME)
                }
                if (data?.getStringExtra(AddServerActivity.RESULT_PASSWORD) != null) {
                    password = data.getStringExtra(AddServerActivity.RESULT_PASSWORD)
                }
                if (data?.getStringExtra(AddServerActivity.RESULT_NAME) != null) {
                    name = data.getStringExtra(AddServerActivity.RESULT_NAME)
                }
                if (data?.getStringExtra(AddServerActivity.RESULT_DOMAIN) != null) {
                    domain = data.getStringExtra(AddServerActivity.RESULT_DOMAIN)
                }
                mPresenter.handleServerAdd(ip, username, password, domain, name)
            }
        }
    }


    //region private

    fun inject() {
        val app = application as YourMediaList
        val activityModule = DaggerActivityComponent.builder()
                .appComponent(app.mAppComponent)
                .activityModule(ActivityModule(this))
                .build()

        activityModule.inject(this)
    }

    //endregion


    //region viewsurface

    override fun navigateToAddServerDialog() {
        startActivityForResult(Intent(this, AddServerActivity::class.java), requestAddServer)
    }

    override fun renderServerList(list: List<MediaServer>) {
        serverAdapter = ArrayAdapter(this, R.layout.list_item_server, list)
        list_servers.adapter = serverAdapter
    }

    override fun showAddServerButton(show: Boolean) {
        add_btn_container.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showButtons(show: Boolean) {
        layout_btn_container.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showServerList(show: Boolean) {
        list_servers.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showServerAddedSnackbar() {
        val sb = Snackbar.make(add_btn_container, "Server added", Snackbar.LENGTH_SHORT)
        sb.show()
    }

    override fun navigateToMediaList(id: Int) {
        val intent = Intent(this, MediaListActivity::class.java)
        intent.putExtra(serverId, id)
        startActivity(intent)
    }

    override fun showLoadingState(show: Boolean) {
        progess_bar_launcher.visibility = if (show) View.VISIBLE else View.GONE
    }


    //endregion

    //region private

    //endregion

}