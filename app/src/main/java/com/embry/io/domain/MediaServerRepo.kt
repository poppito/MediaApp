package com.embry.io.domain

/**
 * Repository pattern for a media
 * server manager of course
 *
 * @author harshoverseer
 */
interface MediaServerRepo {
    fun addMediaServer(name: String, address: String, username: String, password: String)
    fun getAllMediaServers()
    fun removeMediaServer(name: String)
}