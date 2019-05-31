package com.stoyanoff.kingcrimson.presentation.home.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.data.model.post.Post

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */

class PostsAdapter(
    var editClickListener: ((Post) -> Unit)? = null,
    var deleteClickListener: ((Post) -> Unit)? = null
) : RecyclerView.Adapter<PostsAdapter.PostHolder>() {


    private var posts = mutableListOf<Post>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_list_item,parent,false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bindPostItem(posts[position])
    }

    fun setItems(items: MutableList<Post>) {
        posts = items
        notifyDataSetChanged()
    }

    fun addItem(item : Post) {
        posts.add(item)
        notifyItemInserted(posts.indexOf(item))
    }

    inner class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var post : Post? = null

        private val postName = itemView.findViewById<TextView>(R.id.post_name_tv)
        private val postBody = itemView.findViewById<TextView>(R.id.post_body_tv)
        private val deleteView = itemView.findViewById<ImageView>(R.id.delete_view)
        private val editView = itemView.findViewById<ImageView>(R.id.edit_view)

        fun bindPostItem(post: Post) {
            this.post = post
            postName.text = post.title

            postBody.text = post.body

            deleteView.setOnClickListener {
                deleteClickListener?.invoke(post)
            }
            editView.setOnClickListener {
                editClickListener?.invoke(post)
            }
        }
    }
}