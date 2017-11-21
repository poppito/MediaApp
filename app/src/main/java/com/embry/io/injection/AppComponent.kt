package com.embry.io.injection;

import com.embry.io.app.YourMediaList
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: YourMediaList)
}
