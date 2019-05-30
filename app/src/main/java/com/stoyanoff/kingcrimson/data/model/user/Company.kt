package com.stoyanoff.kingcrimson.data.model.user

import com.google.gson.annotations.SerializedName

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
data class Company (
    @SerializedName("name")
    val name:String?,
    @SerializedName("catchPhrase")
    val catchPhrase:String?,
    @SerializedName("bs")
    val bs:String?
)