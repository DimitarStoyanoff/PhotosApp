package com.stoyanoff.kingcrimson.data.model.user

import com.google.gson.annotations.SerializedName

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
data class GeoLocation (
    @SerializedName("lat")
    val lat: String?,
    @SerializedName("lng")
    val lng: String?
)