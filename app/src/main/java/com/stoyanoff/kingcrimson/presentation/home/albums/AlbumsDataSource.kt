package com.stoyanoff.kingcrimson.presentation.home.albums

import com.stoyanoff.kingcrimson.data.model.album.Album
import io.reactivex.Observable


/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
interface AlbumsDataSource {
    fun getAlbums() : Observable<MutableList<Album>>
}