package com.stoyanoff.kingcrimson.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
data class LoginResponse(
    @SerializedName("token")
    val token : String?
)