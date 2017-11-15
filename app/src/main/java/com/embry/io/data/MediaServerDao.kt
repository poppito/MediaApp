package com.embry.io.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Db access object for
 * MediaServer repo
 *
 * @author harshoverseer
 */
@Dao
interface MediaServerDao {

    @Insert
    fun addMediaServer(server: MediaServer)

    @Query("SELECT * from mediaserver")
    fun getAllMediaServers() : List<MediaServer>
}
