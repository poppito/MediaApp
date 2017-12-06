package com.embry.io.presentation.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.embry.io.R
import com.embry.io.data.MediaFile

/**
 * Adapter class for the Media List to be rendered
 *
 * @author harshoverseer
 */
class MediaFileListAdapter(var fileList : ArrayList<MediaFile>) : RecyclerView.Adapter<MediaFileListAdapter.MediaListViewholder>() {

    private val mFileList = fileList

    override fun onBindViewHolder(holder: MediaListViewholder?, position: Int) {
        holder?.fileTitle?.text = mFileList[position].name
    }

    override fun getItemCount(): Int {
        return mFileList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MediaListViewholder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.media_list_row, parent!!, false)
        return MediaListViewholder(itemView)
    }

    class MediaListViewholder(view : View) : RecyclerView.ViewHolder(view) {
        val fileTitle  = view.findViewById<TextView>(R.id.txt_media_file_title) as TextView
        val fileIcon  = view.findViewById<ImageView>(R.id.ic_media_item) as ImageView
    }

}