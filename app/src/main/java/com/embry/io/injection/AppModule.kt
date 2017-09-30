package com.embry.io.injection;

import android.app.Application
import dagger.Module


@Module
class AppModule(private val app: Application) {
}