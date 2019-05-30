package com.stoyanoff.kingcrimson.data

import com.stoyanoff.kingcrimson.data.model.login.LoginResponse
import com.stoyanoff.kingcrimson.data.model.user.UserResponse

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
object SessionHolder {
    var user: UserResponse? = null

    fun logout() {
        user = null
    }
}