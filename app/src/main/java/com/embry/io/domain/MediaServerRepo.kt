package com.embry.io.domain

import com.embry.io.data.MediaServer
import io.reactivex.Observable
import jcifs.smb.SmbFile

/**
 * Repository pattern for a media
 * server manager of course
 *
 * @author harshoverseer
 */
interface MediaServerRepo {
    fun verifyAddServer(ip: String, username: String, password: String, name: String): Observable<Array<SmbFile>>
    fun getAllMediaServers() : Observable<List<MediaServer>>
    fun removeMediaServer(name: String)
    fun addServer(ip: String,
                  username: String,
                  password: String,
                  name: String)
}