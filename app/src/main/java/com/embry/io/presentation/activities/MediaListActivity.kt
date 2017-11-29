package com.embry.io.presentation.activities;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.embry.io.R
import com.embry.io.app.YourMediaList
import com.embry.io.injection.ActivityModule
import com.embry.io.injection.DaggerActivityComponent
import com.embry.io.presentation.presenters.MediaListPresenter
import com.embry.io.presentation.presenters.MediaListPresenter.MainViewSurface
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MediaListActivity : AppCompatActivity(), MainViewSurface {

    @Inject
    lateinit var mPresenter: MediaListPresenter

    private var mLayoutManager: RecyclerView.LayoutManager? = null

    // region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        mPresenter.onStart(this)
        val index =  intent.getIntExtra(LauncherActivity.serverId, 19945)
        if (index != 19945) {
            initialiseRecyclerView(index)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

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
            else -> {
                return true
            }
        }
    }

    //end region

    //region private

    private fun inject() {
        val app = application as YourMediaList
        DaggerActivityComponent.builder()
                .appComponent(app.mAppComponent)
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
    }


    private fun initialiseRecyclerView(id : Int) {
        view_recycler_playables.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)
    }

    //end region
}
