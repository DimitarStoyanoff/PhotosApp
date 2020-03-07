package com.stoyanoff.kingcrimson.presentation.home.posts

import com.stoyanoff.kingcrimson.data.model.post.Post
import com.stoyanoff.kingcrimson.data.remote.SafeResponse
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
interface PostsDataSource {
    suspend fun getPosts() : SafeResponse<MutableList<Post>>
    fun deletePost(postId: Int) : Observable<Response<Post>>
}