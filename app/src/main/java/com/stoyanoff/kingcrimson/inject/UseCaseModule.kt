package com.stoyanoff.kingcrimson.inject

import com.stoyanoff.kingcrimson.presentation.launch.login.LoginUseCase
import org.koin.dsl.module.module

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
val useCaseModule = module{

    factory { LoginUseCase(get()) }
}
