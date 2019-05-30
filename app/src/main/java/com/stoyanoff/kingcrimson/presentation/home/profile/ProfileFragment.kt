package com.stoyanoff.kingcrimson.presentation.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    fun initAlbumsButton() {
        button_albums.setOnClickListener {
            viewModel.albumsButtonClicked()
        }
    }

    fun handleNavigateToAlbumsEvent() {

    }

    override fun toggleLoading(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
        } else progressBar.visibility = View.GONE
    }


}