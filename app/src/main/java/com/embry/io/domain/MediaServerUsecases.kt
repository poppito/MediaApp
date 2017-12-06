package com.embry.io.domain

import com.embry.io.data.MediaServer
import io.reactivex.Observable
import io.reactivex.Single
import jcifs.smb.SmbFile
import javax.inject.Inject

/**
 * use cases to add initial media server
 */
class MediaServerUsecases @Inject constructor(val mediaServerRepo: MediaServerRepo) {

    fun addMediaServer(address: String, username: String, password: String, domain: String, name: String) : Single<Unit> {
        return mediaServerRepo.addServer(address, username, password, domain, name)
    }

    fun getAllMediaServers() : Observable<List<MediaServer>> {
        return mediaServerRepo.getAllMediaServers()
    }

    fun connectToServer(id : Int?) : Single<Array<SmbFile>> {
       return  mediaServerRepo.getMediaServerById(id)
                .flatMap {
                    mediaServerRepo.connectToMediaServer(it)
                }
    }
}
