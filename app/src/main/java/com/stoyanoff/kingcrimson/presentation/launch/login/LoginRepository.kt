package com.stoyanoff.kingcrimson.presentation.launch.login

import com.stoyanoff.kingcrimson.data.model.request.LoginRequest
import com.stoyanoff.kingcrimson.data.model.response.LoginResponse
import com.stoyanoff.kingcrimson.data.remote.RemoteService
import io.reactivex.Observable

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class LoginRepository(
    private val remoteService: RemoteService
) : LoginDataSource {

    override fun login(userId: String): Observable<LoginResponse> {
        return remoteService.login(LoginRequest(userId))
    }
}