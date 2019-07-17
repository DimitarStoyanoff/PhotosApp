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


    internal val viewState = MutableLiveData<AlbumsViewState>().apply {
        postValue(albumsViewState)
    }

    internal val navigateToAlbumDetails = MutableLiveData<Event<Album>>()
    private var albums = mutableListOf<Album>()

    internal fun loadData() {
        compositeDisposable += dataSource.getAlbums()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                toggleLoadingState(true)
            }
            .doOnTerminate {
                toggleLoadingState(false)

            }
            .subscribeBy (onError = { error ->
            }, onNext = { result ->
                result.body()?.let { body ->
                    showResults(body)
                }
                //TODO handle different failed responses
            })
    }

    internal fun toggleLoadingState(showLoading: Boolean) {
        viewState.value?.let {
            val newState = albumsViewState.copy(showLoading = showLoading)
            viewState.value = newState
        }
    }

    internal fun showResults(albums: MutableList<Album>) {
        viewState.value?.let {
            val newState = albumsViewState.copy(showLoading = false, results = albums)
            viewState.value = newState
        }
    }


    internal fun listItemClicked(item : Album) {
        navigateToAlbumDetails.value = Event(item)
    }
}