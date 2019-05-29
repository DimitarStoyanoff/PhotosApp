package com.stoyanoff.kingcrimson.presentation.launch.login

import androidx.lifecycle.MutableLiveData
import com.stoyanoff.kingcrimson.presentation.common.BaseViewModel
import com.stoyanoff.kingcrimson.presentation.common.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class LoginViewModel(
    private val loginViewState: LoginViewState,
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val viewState =MutableLiveData<LoginViewState>().apply {
        value = loginViewState
    }

    val showErrorMessageEvent = MutableLiveData<Event<String>>()
    val navigateToHomeEvent =MutableLiveData<Event<Boolean>>()

    fun onLoginClicked(userId: String) {
        compositeDisposable += loginUseCase.init(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.value?.let {
                    val newState = it.copy(showLoading = true)
                    viewState.value = newState
                }

            }
            .doOnTerminate {
                viewState.value?.let {
                    val newState = it.copy(showLoading = false)
                    viewState.value = newState
                }
            }
            .subscribeBy (onError = {
                error ->

            },
                onNext = {
                    it.token?.let {
                        navigateToHomeEvent.value = Event(true)
                    } ?: run {
                        showErrorMessageEvent.value = Event("") //TODO error handling
                    }
                })
    }
    //TODO disable button while processing request

}