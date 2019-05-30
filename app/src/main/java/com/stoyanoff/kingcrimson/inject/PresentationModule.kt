package com.stoyanoff.kingcrimson.inject

import com.stoyanoff.kingcrimson.presentation.launch.login.LoginViewModel
import com.stoyanoff.kingcrimson.presentation.launch.login.LoginViewState
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
val presentationModule = module {

    viewModel { LoginViewModel(get(), get()) }
    factory { LoginViewState() }
}