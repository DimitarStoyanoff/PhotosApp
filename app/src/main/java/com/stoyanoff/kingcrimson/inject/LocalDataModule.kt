package com.stoyanoff.kingcrimson.inject

import android.content.SharedPreferences
import android.preference.PreferenceManager
import org.koin.dsl.module.module

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
val localDataModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(get()) as SharedPreferences }
}