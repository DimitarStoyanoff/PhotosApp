package com.stoyanoff.kingcrimson.presentation.home.albums

import com.stoyanoff.kingcrimson.data.SessionHolder
import com.stoyanoff.kingcrimson.data.model.album.Album
import com.stoyanoff.kingcrimson.data.remote.RemoteService
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class AlbumsRepository(
    private val remoteService: RemoteService
) : AlbumsDataSource {
    override fun getAlbums(): Observable<Response<MutableList<Album>>> {
        return remoteService.getAlbums(SessionHolder.user?.id ?: 0)
    }

}