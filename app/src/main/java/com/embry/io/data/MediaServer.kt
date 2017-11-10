package com.embry.io.data

import java.net.URI

/** Pretty self explanatory.
 * @author harshoverseer
 */
data class MediaServer(private val uri: URI,
                       private val username:String,
                       private val password:String,
                       private val name: String)