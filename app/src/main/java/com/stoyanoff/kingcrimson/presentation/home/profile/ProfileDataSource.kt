package com.stoyanoff.kingcrimson.presentation.home.profile

import com.stoyanoff.kingcrimson.data.model.user.UserResponse
import io.reactivex.Observable


/**
 * Created by L on 31/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
interface ProfileDataSource {
    fun getProfile() : Observable<UserResponse>
}