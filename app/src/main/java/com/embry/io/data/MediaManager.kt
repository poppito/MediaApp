package com.embry.io.data

import com.embry.io.domain.MediaRepo
import javax.inject.Inject

/**
 * Implementation class for Media Repo
 * interface
 *
 * @author harshoverseer
 */
class MediaManager @Inject constructor(private val db: MediaSourceDb) : MediaRepo {
}
