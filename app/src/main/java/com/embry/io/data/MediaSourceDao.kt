package com.embry.io.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * DB access object for the
 * ORM to work.
 *
 * @author harshoverseer
 */
@Dao
interface MediaSourceDao {

    @Insert
    fun insertItem(item: MediaServer)

    @Delete
    fun deleteItem(item: MediaServer)

    @Query("SELECT * from todoitems WHERE title LIKE :p0")
    fun getMediaSourceByUri(title: String): MediaServer

    @Query("SELECT * from todoitems")
    fun getAllMediaSources() : List<MediaServer>
}