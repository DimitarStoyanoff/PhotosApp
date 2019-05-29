package com.stoyanoff.kingcrimson.inject

import com.stoyanoff.kingcrimson.BuildConfig
import com.stoyanoff.kingcrimson.data.remote.RemoteService
import com.stoyanoff.kingcrimson.data.remote.RetrofitClient
import com.stoyanoff.kingcrimson.data.remote.RetrofitRestClient
import org.koin.dsl.module.module

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */

val appModule = module {
    single<RemoteService> {
        RetrofitRestClient()
            .addCallAdapterFactory()
            .addConverterFactory()
            .addClient()
            .baseUrl(BuildConfig.BASE_URL)
            .createService(RemoteService::class.java)
    }
}