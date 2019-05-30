package com.stoyanoff.kingcrimson.data.remote

import com.stoyanoff.kingcrimson.data.model.album.Album
import com.stoyanoff.kingcrimson.data.model.login.LoginRequest
import com.stoyanoff.kingcrimson.data.model.login.LoginResponse
import com.stoyanoff.kingcrimson.data.model.photo.Photo
import com.stoyanoff.kingcrimson.data.model.post.Post
import com.stoyanoff.kingcrimson.data.model.user.UserResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */

interface RemoteService {

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Int): Observable<UserResponse>

    @GET("posts")
    fun getPosts(@Query("userId=") userId: Int) : Observable<List<Post>>

    @POST("posts")
    fun addPost(@Body body: Post)

    @PUT("posts/{postId}")
    fun updatePost(@Path("postId") postId: Int, @Body body: Post)

    @DELETE("posts/{postId}")
    fun deletePost(@Path("postId") postId: Int)

    @GET("albums")
    fun getAlbums(@Query("userId=") userId: Int) : Observable<List<Album>>

    @GET("photos}")
    fun getPhotosInAlbum(@Query("albumId=") albumId: Int) : Observable<List<Photo>>

}