package com.embry.io.injection

import dagger.Component

@PerScreen
@Component (dependencies = arrayOf(AppComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
}