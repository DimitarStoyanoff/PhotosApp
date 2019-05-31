package com.stoyanoff.kingcrimson.presentation.home.posts

import com.stoyanoff.kingcrimson.data.model.post.Post

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
data class PostsViewState (
    var showLoading: Boolean = false,
    var data: MutableList<Post>? = null
)