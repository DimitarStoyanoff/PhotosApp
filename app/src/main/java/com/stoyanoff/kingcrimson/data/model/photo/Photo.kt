package com.stoyanoff.kingcrimson.data.model.photo

import com.google.gson.annotations.SerializedName

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
data class Photo (
    @SerializedName("albumId")
    val albumId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?
)