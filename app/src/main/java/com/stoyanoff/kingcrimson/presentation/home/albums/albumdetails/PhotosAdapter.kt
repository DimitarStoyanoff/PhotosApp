package com.stoyanoff.kingcrimson.presentation.home.albums.albumdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.data.model.photo.Photo

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class PhotosAdapter(
    var clickListener: ((Photo) -> Unit)? = null
) : RecyclerView.Adapter<PhotosAdapter.PhotoHolder>() {

    private var photos = mutableListOf<Photo>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_list_item,parent,false)
        return PhotoHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bindAlbumItem(photos[position])
    }

    fun setItems(items: MutableList<Photo>) {
        photos = items
        notifyDataSetChanged()
    }

    fun addItem(item : Photo) {
        photos.add(item)
        notifyItemInserted(photos.indexOf(item))
    }


    inner class PhotoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var photoView = itemView.findViewById<ImageView>(R.id.imageView)
        private var photo : Photo? = null


        fun bindAlbumItem(photo: Photo) {
            this.photo = photo

            val requestOptions = RequestOptions()
            with(requestOptions) {
                error(R.drawable.ic_broken_image_black_24dp)
                placeholder(R.drawable.ic_image_black_24dp)
            }

            Glide.with(itemView.context)
                .load(photo.thumbnailUrl)
                .apply(requestOptions)
                .into(photoView)
        }

        init{
            itemView.setOnClickListener{
                photo?.let {
                    clickListener?.invoke(photo!!)
                }
            }
        }
    }
}