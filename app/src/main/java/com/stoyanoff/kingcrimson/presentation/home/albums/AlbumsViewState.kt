package com.stoyanoff.kingcrimson.presentation.home.albums

import com.stoyanoff.kingcrimson.data.model.album.Album

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
data class AlbumsViewState (
    var showLoading: Boolean = false,
    var results: MutableList<Album>? = null
)