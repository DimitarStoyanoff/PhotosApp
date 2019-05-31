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
        initButtons()
        loadUserData()
    }

    override fun initViewModelStates() {
        handleNavigateToAlbumsEvent()
        handleNavigateToPostsEvent()
        handleViewState()
    }

    private fun initButtons() {
        button_albums.setOnClickListener {
            viewModel.albumsButtonClicked()
        }
        button_posts.setOnClickListener {
            viewModel.postsButtonClicked()
        }
    }

    private fun loadUserData() {
        viewModel.loadProfileData()
    }

    private fun handleNavigateToAlbumsEvent() {
        viewModel.navigateToAlbumsEvent.observe(this, Observer {event ->
            event.getContentIfNotHandled()?.let {
                navigateTo(resId = R.id.action_profileFragment_to_albumsFragment)
            }
        })
    }

    private fun handleNavigateToPostsEvent() {
        viewModel.navigateToPostsEvent.observe(this, Observer {event ->
            event.getContentIfNotHandled()?.let {
                navigateTo(R.id.action_profileFragment_to_postsFragment)
            }
        })
    }

    override fun toggleLoading(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
        } else progressBar.visibility = View.GONE
    }
    private fun handleViewState() {
        viewModel.viewState.observe(this, Observer {
            toggleLoading(it.showLoading)
            it.profile?.let { profile ->
                username_tv.text = profile.name
                profile.address?.let { address ->
                    address_tv.text = address.city + ", " + address.street
                }
            }
        })
    }

}