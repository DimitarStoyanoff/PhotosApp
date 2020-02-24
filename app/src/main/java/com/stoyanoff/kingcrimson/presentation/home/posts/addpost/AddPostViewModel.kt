package com.stoyanoff.kingcrimson.presentation.home.posts.addpost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stoyanoff.kingcrimson.data.model.post.Post
import com.stoyanoff.kingcrimson.presentation.common.BaseViewModel
import com.stoyanoff.kingcrimson.presentation.common.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

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
        viewState.value = addPostViewState.copy(showLoading = true)
        viewModelScope.launch {
            dataSource.addPost(title,body)
            navigateBackEvent.value = Event(true)
            showMessageEvent.value = Event("Post added!")
            viewState.value = addPostViewState.copy(showLoading = false)
        }
    }

}