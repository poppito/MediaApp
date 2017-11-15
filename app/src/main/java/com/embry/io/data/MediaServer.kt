package com.embry.io.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/** Pretty self explanatory.
 * @author harshoverseer
 */
@Entity(tableName = "mediaserver")
data class MediaServer(
        @PrimaryKey(autoGenerate = true)  var id: Int,
        var ip: String,
        var username: String,
        var password: String,
        var name: String) {

    override fun toString(): String {
        return String.format("%s (%s)", name, ip)
    }
}