package com.embry.io.data

import com.embry.io.domain.MediaServerRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jcifs.smb.NtlmPasswordAuthentication
import jcifs.smb.SmbFile

/**
 * Implementation class for MediaServerRepo of course.
 *
 * @author harshoverseer
 */
class MediaServerManager : MediaServerRepo {
    override fun addMediaServer(name: String, address: String, username: String, password: String): Observable<Array<SmbFile>> {
        val path = "smb://" + address
        val auth = NtlmPasswordAuthentication(null, username, password)
        val dir = SmbFile(path, auth)
        return Observable.fromCallable {
            verifyServer(dir)
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    override fun getAllMediaServers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeMediaServer(name: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    //region private

    private fun verifyServer(dir: SmbFile): Array<SmbFile> {
        return dir.listFiles()
    }

    //endregion

}