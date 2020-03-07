package com.stoyanoff.kingcrimson.presentation.home.posts

import com.stoyanoff.kingcrimson.data.SessionHolder
import com.stoyanoff.kingcrimson.data.model.post.Post
import com.stoyanoff.kingcrimson.data.remote.RemoteService
import com.stoyanoff.kingcrimson.data.remote.SafeResponse
import com.stoyanoff.kingcrimson.data.remote.ResponseHandler
import io.reactivex.Observable
import retrofit2.Response
import java.lang.Exception

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class PostsRepository(
    private val remoteService: RemoteService,
    private val responseHandler: ResponseHandler
) : PostsDataSource {
    override fun deletePost(postId: Int) : Observable<Response<Post>> {
        return remoteService.deletePost(postId)
    }

    override suspend fun getPosts(): SafeResponse<MutableList<Post>> {
        return try {
             responseHandler.handleSuccess(remoteService.getPosts(SessionHolder.user?.id ?: 0))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }

    }

}