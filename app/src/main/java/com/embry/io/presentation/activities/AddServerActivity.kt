package com.embry.io.presentation.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.embry.io.R
import com.embry.io.app.YourMediaList
import com.embry.io.injection.ActivityModule
import com.embry.io.injection.DaggerActivityComponent
import com.embry.io.presentation.presenters.AddServerPresenter
import kotlinx.android.synthetic.main.activity_add_server.*
import javax.inject.Inject

/**
 * add a server if required.
 */
class AddServerActivity : AppCompatActivity(), AddServerPresenter.ViewSurface, TextWatcher {

    @Inject
    lateinit var mPresenter: AddServerPresenter

    private var ipFilled = false
    private var usernameFilled = false
    private var passwordFilled = false
    private var nameFilled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_server)

        et_server_ip.addTextChangedListener(this)
        et_server_username.addTextChangedListener(this)
        et_server_name.addTextChangedListener(this)
        et_server_password.addTextChangedListener(this)

        inject()

        mPresenter.onStart(this)

        btn_dialog_add_server?.setOnClickListener {
            mPresenter.handleAddServerButtonClick(et_server_ip?.text?.toString()!!,
                    et_server_username?.text?.toString()!!,
                    et_server_password?.text?.toString()!!,
                    et_server_name?.text?.toString()!!)
        }
    }


    override fun afterTextChanged(s: Editable?) {
        if (s?.hashCode() == et_server_ip.text.hashCode()) {
            ipFilled = if (s.isNotEmpty()) true else false
        }
        if (s?.hashCode() == et_server_name.text.hashCode()) {
            nameFilled = if (s.isNotEmpty()) true else false
        }
        if (s?.hashCode() == et_server_username.text.hashCode()) {
            usernameFilled = if (s.isNotEmpty()) true else false
        }
        if (s?.hashCode() == et_server_password.text.hashCode()) {
            passwordFilled = if (s.isNotEmpty()) true else false
        }

        btn_dialog_add_server.isEnabled =
                if (ipFilled && nameFilled && usernameFilled && passwordFilled) true else false
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }


    //region private

    fun inject() {
        val app = application as YourMediaList

        DaggerActivityComponent.builder()
                .appComponent(app.mAppComponent)
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
    }

    //endregion
}