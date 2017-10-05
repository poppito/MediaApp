package com.embry.io.presentation.view.activities;


import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.embry.io.R
import com.embry.io.app.Casterly
import com.embry.io.injection.ActivityModule
import com.embry.io.injection.DaggerActivityComponent
import com.embry.io.presentation.view.presenters.MainPresenter
import com.embry.io.presentation.view.presenters.MainPresenter.MainViewSurface
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainViewSurface {

    @Inject
    lateinit var mPresenter: MainPresenter

    private val mDrawerItems = listOf("A", "B", "C", "D")

    private var actionBarDrawerToggle : ActionBarDrawerToggle? = null

    private var mLayoutManager: RecyclerView.LayoutManager? = null

    private val MOUNT_POINT = "mount/"

    // region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        mPresenter.onStart(this)
        initialiseToolbar()
        initialiseDrawerLayout()
        initialiseRecyclerView()
        runCIFS()
    }

    private fun inject() {
        val app = application as Casterly
        DaggerActivityComponent.builder()
                .appComponent(app.mAppComponent)
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
    }

    private fun initialiseToolbar() {
        setSupportActionBar(toolbar_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toc_black_24px)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    private fun initialiseDrawerLayout() {
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar_main, R.string.title_activity_main, R.string.title_activity_main)
        val adapter = ArrayAdapter(this, R.layout.list_item_drawer, mDrawerItems)
        list_drawer.adapter = adapter
        drawer_layout.addDrawerListener(actionBarDrawerToggle!!)
    }

    private fun initialiseRecyclerView() {
        mLayoutManager = LinearLayoutManager(this)
    }


    private fun runCIFS() {
    }

    //end region
}
