package com.stoyanoff.kingcrimson.presentation.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.presentation.common.BaseViewFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class ProfileFragment : BaseViewFragment() {

    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false)


        return fragmentView
    }

    override fun initUi() {
        initAlbumsButton()
    }

    override fun initViewModelStates() {
        handleNavigateToAlbumsEvent()
    }

    private fun initAlbumsButton() {
        button_albums.setOnClickListener {
            viewModel.albumsButtonClicked()
        }
    }

    private fun handleNavigateToAlbumsEvent() {
        viewModel.navigateToAlbumsEvent.observe(this, Observer {event ->
            event.getContentIfNotHandled()?.let {
                navigateTo(resId = R.id.action_profileFragment_to_albumsFragment)
            }
        })
    }

    override fun toggleLoading(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
        } else progressBar.visibility = View.GONE
    }


}