package com.stoyanoff.kingcrimson.data.model.login

import com.google.gson.annotations.SerializedName

/**
 * Created by L on 27/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
data class LoginRequest(
    @SerializedName("userId")
    val userId: String?
)