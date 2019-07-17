package com.stoyanoff.kingcrimson.presentation.home.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.data.model.album.Album

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumHolder>() {

    lateinit var clickListener: ((Album) -> Unit)
    private var albums = mutableListOf<Album>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_list_item,parent,false)
        return AlbumHolder(view)
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.bindAlbumItem(albums[position])
    }

    fun setItems(items: MutableList<Album>) {
        albums = items
        notifyDataSetChanged()
    }

    fun addItem(item : Album) {
        albums.add(item)
        notifyItemInserted(albums.indexOf(item))
    }


    inner class AlbumHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var albumName = itemView.findViewById<TextView>(R.id.album_name_tv)
        var album : Album? = null


        fun bindAlbumItem(album: Album) {
            albumName.text = album.title
            this.album = album
        }

        init{
            itemView.setOnClickListener{
                album?.let {
                    clickListener?.invoke(album!!)
                }
            }
        }
    }
}