package com.embry.io.domain

import com.embry.io.data.MediaSource
import io.reactivex.Single
import java.net.URI

/**
 * Represents the repos of
 * media sources
 * @author harshoverseer
 */
interface MediaRepository {
    fun getSourceByTitle(title: String) : Single<MediaSource>
    fun getAllRepos() : Single<List<MediaSource>>
    fun addASource(uri: URI, username: String, password: String, domain : String?, title: String)
}