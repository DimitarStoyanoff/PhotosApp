package com.stoyanoff.kingcrimson.presentation.home.profile

import androidx.lifecycle.MutableLiveData
import com.stoyanoff.kingcrimson.presentation.common.BaseViewModel
import com.stoyanoff.kingcrimson.presentation.common.Event

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class ProfileViewModel(
    private var profileViewState: ProfileViewState
) : BaseViewModel() {

    val viewState = MutableLiveData<ProfileViewState>().apply {
        value = profileViewState
    }

    val navigateToAlbumsEvent = MutableLiveData<Event<Boolean>>()

    fun albumsButtonClicked() {
        navigateToAlbumsEvent.value = Event(true)
    }
}