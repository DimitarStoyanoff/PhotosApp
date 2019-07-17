package com.stoyanoff.kingcrimson.presentation.home.profile

import androidx.lifecycle.MutableLiveData
import com.stoyanoff.kingcrimson.presentation.common.BaseViewModel
import com.stoyanoff.kingcrimson.presentation.common.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class ProfileViewModel(
    private var profileViewState: ProfileViewState,
    private val profileDataSource: ProfileDataSource
) : BaseViewModel() {

    val viewState = MutableLiveData<ProfileViewState>().apply {
        value = profileViewState
    }

    val navigateToAlbumsEvent = MutableLiveData<Event<Boolean>>()
    val navigateToPostsEvent = MutableLiveData<Event<Boolean>>()

    fun loadProfileData() {
        compositeDisposable += profileDataSource.getProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate {
                viewState.value?.let {
                    val newState = profileViewState.copy(showLoading = false)
                    viewState.value = newState
                }
            }.doOnSubscribe {
                viewState.value?.let {
                    val newState = profileViewState.copy(showLoading = true)
                    viewState.value = newState
                }
            }.subscribeBy (onNext = { result ->
                result.body()?.let {body ->
                    viewState.value?.let {
                        val newState = profileViewState.copy(showLoading = false, profile = body)
                        viewState.value = newState
                    }
                }
            }, onError = {

            })
    }

    fun albumsButtonClicked() {
        navigateToAlbumsEvent.value = Event(true)
    }
    fun postsButtonClicked() {
        navigateToPostsEvent.value = Event(true)
    }
}