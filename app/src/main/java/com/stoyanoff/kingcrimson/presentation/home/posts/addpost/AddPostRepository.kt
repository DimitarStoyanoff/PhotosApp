package com.stoyanoff.kingcrimson.presentation.home.posts.addpost

import com.stoyanoff.kingcrimson.data.SessionHolder
import com.stoyanoff.kingcrimson.data.model.post.Post
import com.stoyanoff.kingcrimson.data.remote.RemoteService
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by L on 31/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class AddPostRepository (
    private val service: RemoteService
) : AddPostDataSource {
    override fun addPost(title: String, body: String): Observable<Response<Post>> {
        return service.addPost(Post(SessionHolder.user?.id ?:0,
            1,title,body))
    }

}