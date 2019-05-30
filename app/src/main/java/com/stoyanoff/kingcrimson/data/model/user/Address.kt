package com.stoyanoff.kingcrimson.data.model.user

import com.google.gson.annotations.SerializedName

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
data class Address(
    @SerializedName("street")
    val street: String?,
    @SerializedName("suite")
    val suite: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("zipcode")
    val zipcode: String?,
    @SerializedName("geo")
    val geo: GeoLocation?

)