package com.embry.io.domain

import com.embry.io.data.MediaServer
import io.reactivex.Single
import java.net.URI

/**
 * Represents the repos of
 * media sources
 * @author harshoverseer
 */
interface MediaRepo {
    fun getSourceByTitle(title: String) : Single<MediaServer>
    fun getAllRepos() : Single<List<MediaServer>>
    fun addASource(uri: URI, username: String, password: String, name: String)
}