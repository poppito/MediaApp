package com.embry.io.injection;

import android.app.Activity
import android.content.Context
import com.embry.io.data.MediaManager
import com.embry.io.data.MediaServerManager
import com.embry.io.data.MediaSourceDb
import com.embry.io.domain.MediaRepo
import com.embry.io.domain.MediaServerRepo
import dagger.Module
import dagger.Provides

@PerScreen
@Module(includes = arrayOf(AppModule::class))
class ActivityModule(private val activity: Activity) {

    @Provides
    fun getContext(): Context = activity

    @Provides
    fun getMediaServerRepo() : MediaServerRepo {
        return MediaServerManager()
    }

    @Provides
    fun getMediaRepo(db: MediaSourceDb) : MediaRepo {
        return MediaManager(db)
    }

}