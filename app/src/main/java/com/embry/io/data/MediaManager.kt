package com.embry.io.data

import com.embry.io.domain.MediaRepository
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
class MediaManager @Inject constructor(private val db: MediaSourceDb) : MediaRepository {

    override fun getAllRepos(): Single<List<MediaSource>> {
        return Single.fromCallable { db.toDoDao().getAllMediaSources() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getSourceByTitle(title: String) : Single<MediaSource> {
        return Single.fromCallable { db.toDoDao().getMediaSourceByUri(title) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addASource(uri: URI, username: String, password: String, domain: String?, title: String) {
        var insertDomain = ""
        if (domain != null) {
            insertDomain = domain
        }

        val source = MediaSource(uri, username, password, insertDomain, title)

        Single.fromCallable { db.toDoDao().insertItem(source) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
