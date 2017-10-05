package com.embry.io.data

import java.net.URI

/** Pretty self explanatory.
 * @author harshoverseer
 */
data class MediaSource(private val uri: URI, private val username:String, private val password:String) {
    val mUri = uri
    val mUsername = username
    val mPassword = password
}