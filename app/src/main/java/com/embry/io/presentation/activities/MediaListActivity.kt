package com.embry.io.presentation.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.embry.io.R
import com.embry.io.app.YourMediaList
import com.embry.io.data.MediaFile
import com.embry.io.injection.ActivityModule
import com.embry.io.injection.DaggerActivityComponent
import com.embry.io.presentation.presenters.MediaListPresenter
import com.embry.io.presentation.presenters.MediaListPresenter.MainViewSurface
import com.embry.io.presentation.view.MediaFileListAdapter
import jcifs.smb.SmbFile
import kotlinx.android.synthetic.main.activity_media_list.*
import javax.inject.Inject

class MediaListActivity : AppCompatActivity(), MainViewSurface, MediaFileListAdapter.OnItemClickListener {

    @Inject
    lateinit var mPresenter: MediaListPresenter

    private var mLayoutManager: RecyclerView.LayoutManager? = null

    // region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_list)
        inject()
        mPresenter.onStart(this)
        val index =  intent.getIntExtra(LauncherActivity.serverId, 19945)
        if (index != 19945) {
            mPresenter.connectToServer(index)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_menu_main_cast -> {
                return true
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> {
                return true
            }
        }
    }

    //end region

    //region viewsurface

    override fun showLoadingState(show: Boolean) {
        progress_bar_media_list.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun renderFileList(mediaList: Array<SmbFile>, id: Int) {
        val mediaFileList = arrayListOf<MediaFile>()
        mediaList.forEach {
            mediaFileList.add(MediaFile(it.isDirectory, it.name, "meh", id))
        }
        mLayoutManager = LinearLayoutManager(this)
        val adapter  = MediaFileListAdapter(mediaFileList, this)
        view_recycler_playables.layoutManager = mLayoutManager
        view_recycler_playables.setHasFixedSize(true)
        view_recycler_playables.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showMediaList(show: Boolean) {
        view_recycler_playables.visibility = if (show) View.VISIBLE else View.GONE
    }

    //endregion

    //region

    override fun onItemClick(file: MediaFile) {
        if (file.isDirectory) {
            mPresenter.handleFileItemClick(file)
        }
    }

    //endregion

    //region private

    private fun inject() {
        val app = application as YourMediaList
        DaggerActivityComponent.builder()
                .appComponent(app.mAppComponent)
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
    }

    //end region
}
