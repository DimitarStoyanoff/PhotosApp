package com.stoyanoff.kingcrimson.presentation.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.presentation.common.BaseViewFragment

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class ProfileFragment : BaseViewFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false)


        return fragmentView
    }

    override fun initUi() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViewModelStates() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toggleLoading(isVisible: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}