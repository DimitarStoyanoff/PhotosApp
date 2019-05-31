package com.stoyanoff.kingcrimson.presentation.home.posts.addpost

import androidx.lifecycle.MutableLiveData
import com.stoyanoff.kingcrimson.data.model.post.Post
import com.stoyanoff.kingcrimson.presentation.common.BaseViewModel
import com.stoyanoff.kingcrimson.presentation.common.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 31/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class AddPostViewModel (
    private val addPostViewState: AddPostViewState,
    private val dataSource: AddPostDataSource
) : BaseViewModel() {

    val showMessageEvent = MutableLiveData<Event<String>>()
    val navigateBackEvent = MutableLiveData<Event<Boolean>>()
    val viewState = MutableLiveData<AddPostViewState>().apply {
        value = addPostViewState
    }

    fun postButtonClicked(title : String, body : String) {
        compositeDisposable += dataSource.addPost(title,body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.value?.let {
                    val newState = addPostViewState.copy(showLoading = true)
                    viewState.value = newState
                }
            }.doOnTerminate {
                viewState.value?.let {
                    val newState = addPostViewState.copy(showLoading = false)
                    viewState.value = newState
                }
            }.subscribeBy(onError = {
                showMessageEvent.value = Event("Something went wrong") //TODO extract resources, move to view layer
            },onNext = {
                navigateBackEvent.value = Event(true)
                showMessageEvent.value = Event("Post added!")
            })
    }

}