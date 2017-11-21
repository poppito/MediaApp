package com.embry.io.domain

import com.embry.io.data.MediaServer
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * use cases to add initial media server
 */
class LauncherUsecases @Inject constructor(val mediaServerRepo: MediaServerRepo) {

    fun addMediaServer(address: String, username: String, password: String, name: String) : Single<Unit> {
        return mediaServerRepo.addServer(address, username, password, name)
    }

    fun getAllMediaServers() : Observable<List<MediaServer>> {
        return mediaServerRepo.getAllMediaServers()
    }
}
