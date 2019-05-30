package com.stoyanoff.kingcrimson.presentation.home.profile

import com.stoyanoff.kingcrimson.data.SessionHolder
import com.stoyanoff.kingcrimson.data.model.user.UserResponse
import com.stoyanoff.kingcrimson.data.remote.RemoteService
import io.reactivex.Observable

/**
 * Created by L on 31/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class ProfileRepository(
    private val remoteService: RemoteService
) : ProfileDataSource {
    override fun getProfile(): Observable<UserResponse> {
        return remoteService.getUser(SessionHolder.user?.id ?: 0)
    }

}