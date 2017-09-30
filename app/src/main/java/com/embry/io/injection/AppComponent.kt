package com.embry.io.injection;

import com.embry.io.app.Casterly
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: Casterly)
}
