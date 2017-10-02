package com.embry.io.injection;

import com.embry.io.presentation.activities.MainActivity
import dagger.Component

@PerScreen
@Component(dependencies=arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(activity: MainActivity)
}