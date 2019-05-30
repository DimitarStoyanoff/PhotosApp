package com.stoyanoff.kingcrimson.presentation.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.presentation.common.BaseActivity

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class HomeActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initNavigationComponents()

    }

    private fun initNavigationComponents() {
        val navigationFragment = supportFragmentManager
            .findFragmentById(R.id.nav_home_host_fragment) as NavHostFragment ?: return
        navController = navigationFragment.navController
    }
}