package com.stoyanoff.kingcrimson.data.remote

import com.stoyanoff.kingcrimson.data.model.request.LoginRequest
import com.stoyanoff.kingcrimson.data.model.response.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */

interface RemoteService {

    @POST("users")
    fun login(@Body body: LoginRequest): Observable<LoginResponse>

}