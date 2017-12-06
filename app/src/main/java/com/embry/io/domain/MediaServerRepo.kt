package com.embry.io.domain

import com.embry.io.data.MediaServer
import io.reactivex.Observable
import io.reactivex.Single
import jcifs.smb.SmbFile

/**
 * Repository pattern for a media
 * server manager of course
 *
 * @author harshoverseer
 */
interface MediaServerRepo {
    fun verifyAddServer(ip: String, username: String, password: String, domain: String, name: String): Observable<Array<SmbFile>>
    fun getAllMediaServers(): Observable<List<MediaServer>>
    fun removeMediaServer(name: String)
    fun addServer(ip: String,
                  username: String,
                  password: String,
                  domain: String,
                  name: String): Single<Unit>

    fun connectToMediaServer(server: MediaServer): Single<Array<SmbFile>>

    fun getMediaServerById(id: Int): Single<MediaServer>

    fun connectToMediaServer(server : MediaServer, path: String) : Single<Array<SmbFile>>
}