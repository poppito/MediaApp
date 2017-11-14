package com.embry.io.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/** Pretty self explanatory.
 * @author harshoverseer
 */
@Entity(tableName = "mediaserver")

data class MediaServer(
        @PrimaryKey(autoGenerate = true) private val id: Int,
        private val ip: String,
        private val username: String,
        private val password: String,
        private val name: String)