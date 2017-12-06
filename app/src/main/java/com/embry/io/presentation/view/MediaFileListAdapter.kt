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
class MediaFileListAdapter(var files : ArrayList<MediaFile>, listener: OnItemClickListener) : RecyclerView.Adapter<MediaFileListAdapter.MediaListViewholder>() {

    private val fileList = files
    private val itemClickListener = listener

    override fun onBindViewHolder(holder: MediaListViewholder?, position: Int) {
        holder?.fileTitle?.text = this.fileList[position].name
        holder?.bind(this.fileList[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return this.fileList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MediaListViewholder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.media_list_row, parent!!, false)
        return MediaListViewholder(itemView)
    }

    class MediaListViewholder(val view : View) : RecyclerView.ViewHolder(view) {

        val fileTitle  = view.findViewById<TextView>(R.id.txt_media_file_title) as TextView
        val fileIcon  = view.findViewById<ImageView>(R.id.ic_media_item) as ImageView

        fun bind(file: MediaFile, listener: OnItemClickListener) {
            view.setOnClickListener { listener.onItemClick(file) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(file: MediaFile)
    }

}