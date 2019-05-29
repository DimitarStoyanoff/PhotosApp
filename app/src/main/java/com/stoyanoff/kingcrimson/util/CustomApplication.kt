package com.stoyanoff.kingcrimson.util

import android.app.Application
import com.stoyanoff.kingcrimson.inject.appModule
import com.stoyanoff.kingcrimson.inject.dataModule
import com.stoyanoff.kingcrimson.inject.presentationModule
import com.stoyanoff.kingcrimson.inject.useCaseModule
import org.koin.android.ext.android.startKoin

/**
 * Created by L on 27/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class CustomApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        initDependencyInjectionFramework()
    }

    private fun initDependencyInjectionFramework() {
        startKoin(this, listOf(
            appModule,
            dataModule,
            useCaseModule,
            presentationModule))
    }
}