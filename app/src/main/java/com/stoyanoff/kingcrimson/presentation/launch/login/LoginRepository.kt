package com.stoyanoff.kingcrimson.presentation.launch.login

import com.stoyanoff.kingcrimson.data.model.login.LoginRequest
import com.stoyanoff.kingcrimson.data.model.login.LoginResponse
import com.stoyanoff.kingcrimson.data.model.user.UserResponse
import com.stoyanoff.kingcrimson.data.remote.RemoteService
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class LoginRepository(
    private val remoteService: RemoteService
) : LoginDataSource {

    override fun login(userId: Int): Observable<Response<UserResponse>> {
        return remoteService.getUser(userId)
    }
}