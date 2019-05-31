package com.stoyanoff.kingcrimson.presentation.home.posts.addpost

import com.stoyanoff.kingcrimson.data.model.post.Post
import io.reactivex.Observable

/**
 * Created by L on 31/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
interface AddPostDataSource {
    fun addPost(title: String, body: String) : Observable<Post>
}