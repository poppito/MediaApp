package com.embry.io.domain

import io.reactivex.Observable
import jcifs.smb.SmbFile

/**
 * Repository pattern for a media
 * server manager of course
 *
 * @author harshoverseer
 */
interface MediaServerRepo {
    fun addMediaServer(name: String, address: String, username: String, password: String) : Observable<Array<SmbFile>>
    fun getAllMediaServers()
    fun removeMediaServer(name: String)
}