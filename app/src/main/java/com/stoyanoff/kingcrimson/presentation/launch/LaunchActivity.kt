package com.stoyanoff.kingcrimson.presentation.launch

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.presentation.common.BaseActivity

class LaunchActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        initNavigationComponents()
    }

    private fun initNavigationComponents() {
        val navigationFragment = supportFragmentManager
            .findFragmentById(R.id.nav_launch_host_fragment) as NavHostFragment ?: return

        navController = navigationFragment.navController
    }
}
