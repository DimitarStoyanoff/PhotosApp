package com.stoyanoff.kingcrimson.presentation.home.albums

import androidx.lifecycle.MutableLiveData
import com.stoyanoff.kingcrimson.data.model.album.Album
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
class AlbumsViewModel(
    private val albumsViewState: AlbumsViewState,
    private val dataSource: AlbumsDataSource
) : BaseViewModel() {


    val viewState = MutableLiveData<AlbumsViewState>().apply {
        value = albumsViewState
    }

    val navigateToAlbumDetails = MutableLiveData<Event<Album>>()
    private var albums = mutableListOf<Album>()

    fun loadData() {
        compositeDisposable += dataSource.getAlbums()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.value?.let {
                    val newState = albumsViewState.copy(showLoading = true)
                    viewState.value = newState
                }
            }
            .doOnTerminate {
                viewState.value?.let {
                    val newState = albumsViewState.copy(showLoading = false)
                    viewState.value = newState
                }
            }
            .subscribeBy (onError = { error ->
            }, onNext = { result ->
                result?.let {
                    albums = it
                    viewState.value?.let {
                        val newState = albumsViewState.copy(showLoading = false, results = result)
                        viewState.value = newState
                    }
                }
            })
    }


    fun listItemClicked(item : Album) {
        navigateToAlbumDetails.value = Event(item)
    }
}