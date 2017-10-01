package data

import java.net.URI

/**
 * represents a video of course.
 * @author harshoverseer
 */
data class Video(private val title: String, private val uri : URI) {

    val mTitle: String = title
    val mUri: URI = uri
}