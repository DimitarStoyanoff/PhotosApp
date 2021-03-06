package com.stoyanoff.kingcrimson.presentation.home.albums.albumdetails

import com.stoyanoff.kingcrimson.data.model.photo.Photo
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
interface AlbumDetailsDataSource {
    fun getPhotos(albumId: Int) : Observable<Response<MutableList<Photo>>>
}