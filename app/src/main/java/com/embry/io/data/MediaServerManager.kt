package com.embry.io.data

import android.util.Log
import com.embry.io.domain.MediaServerRepo
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jcifs.smb.NtlmPasswordAuthentication
import jcifs.smb.SmbFile
import javax.inject.Inject

/**
 * Implementation class for MediaServerRepo of course.
 *
 * @author harshoverseer
 */
class MediaServerManager @Inject constructor(private val mediaServerDb: MediaServerDb) : MediaServerRepo {

    override fun verifyAddServer(ip: String, username: String, password: String, domain: String, name: String): Observable<Array<SmbFile>> {
        val auth = NtlmPasswordAuthentication(domain, username, password)
        val dir = SmbFile(sanitisePath(ip), auth)
        return Observable.fromCallable {
            verifyServer(dir)
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    override fun getAllMediaServers() : Observable<List<MediaServer>> {
        return Observable.fromCallable {
            mediaServerDb.mediaServerDao().getAllMediaServers()
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    override fun removeMediaServer(name: String) {
        //ToDo dao update required.
    }

    override fun addServer(ip: String,
                           username: String,
                           password: String,
                           domain: String,
                           name: String) : Single<Unit> {
       return  Single.fromCallable {
            addServer(MediaServer(0, ip, username, password, domain, name))
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    //region private

    private fun verifyServer(dir: SmbFile): Array<SmbFile> {
        dir.listFiles().forEach { Log.v("TAG", it.name) }
        return dir.listFiles()
    }

    private fun addServer(server: MediaServer) {
        mediaServerDb.mediaServerDao().addMediaServer(server)
    }

    private fun sanitisePath(ip: String) : String {
        if (!ip.toLowerCase().startsWith("smb://") || (!ip.toLowerCase().startsWith("cifs://"))) {
            return String.format("%s%s", "smb://", ip)
        }
        return ip;
    }

    //endregion

}