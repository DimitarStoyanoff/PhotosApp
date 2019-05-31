package com.stoyanoff.kingcrimson.presentation.home.posts

import com.stoyanoff.kingcrimson.data.model.post.Post
import io.reactivex.Observable

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
interface PostsDataSource {
    fun getPosts() : Observable<MutableList<Post>>
    fun deletePost(postId: Int) : Observable<Post>
}