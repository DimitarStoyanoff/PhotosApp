package com.stoyanoff.kingcrimson.presentation.home.posts

import androidx.lifecycle.MutableLiveData
import com.stoyanoff.kingcrimson.data.model.post.Post
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

class PostsViewModel(
    private val postsViewState: PostsViewState,
    private val dataSource: PostsDataSource
) : BaseViewModel() {


    val viewState = MutableLiveData<PostsViewState>().apply {
        value = postsViewState
    }

    private var posts = mutableListOf<Post>()

    fun loadData() {
        compositeDisposable += dataSource.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.value?.let {
                    val newState = postsViewState.copy(showLoading = true)
                    viewState.value = newState
                }
            }
            .doOnTerminate {
                viewState.value?.let {
                    val newState = postsViewState.copy(showLoading = false)
                    viewState.value = newState
                }
            }
            .subscribeBy (onError = { error ->
            }, onNext = { result ->
                result?.let {
                    posts = it
                    viewState.value?.let {
                        val newState = postsViewState.copy(showLoading = false, data = result)
                        viewState.value = newState
                    }
                }
            })
    }


    fun listItemClicked(item : Post) {

    }
}