package com.stoyanoff.kingcrimson.presentation.home.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.stoyanoff.kingcrimson.data.model.post.Post
import com.stoyanoff.kingcrimson.data.remote.SafeResponse
import com.stoyanoff.kingcrimson.presentation.common.BaseViewModel
import com.stoyanoff.kingcrimson.presentation.common.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */

class PostsViewModel(
    private val postsViewState: PostsViewState,
    private val dataSource: PostsDataSource
) : BaseViewModel() {

    val viewState = MutableLiveData<PostsViewState>().apply {
        value = postsViewState
    }
    private val input = MutableLiveData<SafeResponse<MutableList<Post>>>()
    val postsResponseLiveData = input.switchMap {
        liveData {
            emit(SafeResponse.loading(null))
            emit(it)
        }
    }

    val navigateToAddPost = MutableLiveData<Event<Boolean>>()
    val navigateToEditPost = MutableLiveData<Event<Post>>()
    private var posts = mutableListOf<Post>()

    fun loadData() {
        viewModelScope.launch {
            val result = dataSource.getPosts()
            input.value = result
            result.data?.let {
                posts = it
            }
        }
    }

    fun addButtonClicked() {
        navigateToAddPost.value = Event(true)
    }

    fun listItemDeleteClicked(item : Post) {
        item.id?.let {
            compositeDisposable += dataSource.deletePost(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    viewState.value?.let {
                        val newState = postsViewState.copy(showLoading = true)
                        viewState.value = newState
                    }
                }.doOnTerminate {
                    viewState.value?.let {
                        val newState = postsViewState.copy(showLoading = false)
                        viewState.value = newState
                    }
                }.subscribeBy(onError = {
                    viewState.value?.let {
                        val newState = postsViewState.copy(showLoading = false)
                        viewState.value = newState
                    }
                },onNext = {
                    posts.remove(item)
                    viewState.value?.let {
                        val newState = postsViewState.copy(showLoading = false, data = posts)
                        viewState.value = newState
                    }
                })
        }

    }

    fun listItemEditClicked(item : Post) {
        navigateToEditPost.value = Event(item)
    }
}