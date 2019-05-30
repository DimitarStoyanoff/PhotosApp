package com.stoyanoff.kingcrimson.presentation.home.albums.albumdetails

import com.stoyanoff.kingcrimson.data.model.photo.Photo
import com.stoyanoff.kingcrimson.data.remote.RemoteService
import io.reactivex.Observable

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class AlbumDetailsRepository (
    private val remoteService: RemoteService
) : AlbumDetailsDataSource {
    override fun getPhotos(albumId: Int): Observable<MutableList<Photo>> {
        return remoteService.getPhotosInAlbum(albumId)
    }

}