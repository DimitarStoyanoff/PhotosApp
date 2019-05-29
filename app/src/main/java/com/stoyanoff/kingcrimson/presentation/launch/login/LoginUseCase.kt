package com.stoyanoff.kingcrimson.presentation.launch.login

import com.stoyanoff.kingcrimson.data.model.response.LoginResponse
import io.reactivex.Observable

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class LoginUseCase(
    private val loginDataSource: LoginDataSource
) {

    fun init(userId: String): Observable<LoginResponse> {

        return loginDataSource.login(userId)
    }
}