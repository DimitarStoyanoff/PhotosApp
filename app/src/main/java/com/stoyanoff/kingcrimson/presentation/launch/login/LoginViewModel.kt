package com.stoyanoff.kingcrimson.presentation.launch.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.stoyanoff.kingcrimson.data.SessionHolder
import com.stoyanoff.kingcrimson.presentation.common.BaseViewModel
import com.stoyanoff.kingcrimson.presentation.common.Event
import com.stoyanoff.kingcrimson.util.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.lang.NumberFormatException

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class LoginViewModel(
    private val loginViewState: LoginViewState,
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val viewState = MutableLiveData<LoginViewState>().apply {
        value = loginViewState
    }

    val showErrorMessageEvent = MutableLiveData<Event<String>>()
    val navigateToHomeEvent = MutableLiveData<Event<Boolean>>()

    fun onLoginClicked(userId: String) {
        var id = 0
        try {
            id = Integer.parseInt(userId)
        } catch (e: NumberFormatException) {
        }
        compositeDisposable += loginUseCase.init(id)
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
                showErrorMessageEvent.value = Event("User doesn't exist")
            },
                onNext = { response ->
                    response.body()?.let {body ->
                        body.name?.let {
                            navigateToHomeEvent.value = Event(true)
                            SessionHolder.user = body
                        } ?: run {
                            showErrorMessageEvent.value = Event("User doesn't exist") //TODO error handling with error codes
                        }
                    }

                })
    }
    //TODO disable button while processing request

}