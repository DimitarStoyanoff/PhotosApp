package com.stoyanoff.kingcrimson.presentation.home.posts

import com.stoyanoff.kingcrimson.data.SessionHolder
import com.stoyanoff.kingcrimson.data.model.post.Post
import com.stoyanoff.kingcrimson.data.remote.RemoteService
import io.reactivex.Observable

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class PostsRepository(
    private val remoteService: RemoteService
) : PostsDataSource {
    override fun getPosts(): Observable<MutableList<Post>> {
        return remoteService.getPosts(SessionHolder.user?.id ?: 0)
    }

}