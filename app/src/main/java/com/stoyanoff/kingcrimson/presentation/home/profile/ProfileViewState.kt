package com.stoyanoff.kingcrimson.presentation.home.profile

import com.stoyanoff.kingcrimson.data.model.user.UserResponse

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
data class ProfileViewState(
    var showLoading: Boolean = false,
    var profile: UserResponse? = null
)