package com.embry.io.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * An abstract representation
 * of the {@link MediaServer} database
 *
 * @author harshoverseer
 */
@Database(entities = arrayOf(MediaServer::class), version = 1)
abstract class MediaServerDb : RoomDatabase() {
    abstract fun mediaServerDao() : MediaServerDao
}
