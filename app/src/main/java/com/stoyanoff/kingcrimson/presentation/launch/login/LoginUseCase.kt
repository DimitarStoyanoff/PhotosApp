package com.stoyanoff.kingcrimson.presentation.launch.login

import com.stoyanoff.kingcrimson.data.model.login.LoginResponse
import com.stoyanoff.kingcrimson.data.model.user.UserResponse
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class LoginUseCase(
    private val loginDataSource: LoginDataSource
) {

    fun init(userId: Int): Observable<Response<UserResponse>> {

        return loginDataSource.login(userId)
    }
}