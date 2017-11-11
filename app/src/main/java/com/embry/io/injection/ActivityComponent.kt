package com.embry.io.injection;

import com.embry.io.presentation.activities.AddServerActivity
import com.embry.io.presentation.activities.LauncherActivity
import com.embry.io.presentation.activities.MediaListActivity
import dagger.Component

@PerScreen
@Component(dependencies=arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(activity: MediaListActivity)
    fun inject(activity: LauncherActivity)
    fun inject(activity: AddServerActivity)
}