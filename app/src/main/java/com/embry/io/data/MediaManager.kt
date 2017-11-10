package com.embry.io.data

import com.embry.io.domain.MediaRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.URI
import javax.inject.Inject

/**
 * Implementation class for Media Repo
 * interface
 *
 * @author harshoverseer
 */
class MediaManager @Inject constructor(private val db: MediaSourceDb) : MediaRepo {

    override fun getAllRepos(): Single<List<MediaServer>> {
        return Single.fromCallable { db.toDoDao().getAllMediaSources() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getSourceByTitle(title: String) : Single<MediaServer> {
        return Single.fromCallable { db.toDoDao().getMediaSourceByUri(title) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addASource(uri: URI, username: String, password: String, name: String) {
        val source = MediaServer(uri, username, password, name)

        Single.fromCallable { db.toDoDao().insertItem(source) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
