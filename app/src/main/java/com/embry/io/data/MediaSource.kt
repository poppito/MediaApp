package com.embry.io.data

import java.net.URI

/** Pretty self explanatory.
 * @author harshoverseer
 */
data class MediaSource(private val uri: URI,
                       private val username:String,
                       private val password:String,
                       private val domain : String,
                       private val title: String) {
}