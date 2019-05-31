package com.stoyanoff.kingcrimson.presentation.home.albums.albumdetails

import com.stoyanoff.kingcrimson.data.model.photo.Photo

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
data class AlbumDetailsViewState(
    var showLoading: Boolean = false,
    var results: MutableList<Photo>? = null
)