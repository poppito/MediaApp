package com.embry.io.injection

import android.content.Context
import android.support.v4.app.Fragment
import com.embry.io.app.YourMediaList
import com.embry.io.data.MediaServerDb
import dagger.Module
import dagger.Provides

@PerScreen
@Module(includes = arrayOf(AppModule::class))
class FragmentModule(private val fragment: Fragment) {

    @Provides
    fun getContext(): Context = fragment.activity

    @Provides
    fun getMediaServerDb() : MediaServerDb {
        val app = fragment.activity.application as YourMediaList
        return app.mMediaServerDb
    }
}