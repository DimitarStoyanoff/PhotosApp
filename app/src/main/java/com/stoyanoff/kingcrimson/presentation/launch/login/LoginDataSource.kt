package com.stoyanoff.kingcrimson.presentation.launch.login

import com.stoyanoff.kingcrimson.data.model.response.LoginResponse
import io.reactivex.Observable

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
interface LoginDataSource {

    fun login(userId: String): Observable<LoginResponse>
}