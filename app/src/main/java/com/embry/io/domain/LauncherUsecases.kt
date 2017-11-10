package com.embry.io.domain

import javax.inject.Inject

/**
 * use cases to add initial media server
 */
class LauncherUsecases @Inject constructor(val mediaServerRepo: MediaServerRepo) {

    fun addMediaServer(address: String, username: String, password: String, name: String) {
        mediaServerRepo.addMediaServer(address, username, password, name)
    }
}
