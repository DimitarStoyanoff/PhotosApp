package com.stoyanoff.kingcrimson.inject

import com.stoyanoff.kingcrimson.presentation.launch.login.LoginDataSource
import com.stoyanoff.kingcrimson.presentation.launch.login.LoginRepository
import org.koin.dsl.module.module

/**
 * Created by L on 29/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
val dataModule = module {

    factory<LoginDataSource> { LoginRepository(get()) }
}