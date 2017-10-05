package com.embry.io.data

import android.provider.BaseColumns

/**
 * Required to create SQLite entries.
 */
class MediaEntryContract {

    private fun MediaEntryContract() {
    }

    companion object MediaSourceEntry : BaseColumns {
        val TABLE_NAME = "MediaSourceEntries"
        val COLUMN_NAME_NAME = "name"
        val COLUMN_NAME_URI = "uri"
        val COLUMN_NAME_USERNAME = "username"
        val COLUMN_NAME_PASSWORD = "password"
        val COLUMN_NAME_DOMAIN = "domain"
    }
}
