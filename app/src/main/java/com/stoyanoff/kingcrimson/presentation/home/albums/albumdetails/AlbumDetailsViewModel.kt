package com.stoyanoff.kingcrimson.presentation.home.albums.albumdetails

import androidx.lifecycle.MutableLiveData
import com.stoyanoff.kingcrimson.data.model.photo.Photo
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
class AlbumDetailsViewModel(
    private val albumDetailsViewState: AlbumDetailsViewState,
    private val dataSource: AlbumDetailsDataSource
) : BaseViewModel() {

    val viewState = MutableLiveData<AlbumDetailsViewState>().apply {
        value = albumDetailsViewState
    }

    val previewPhotoState = MutableLiveData<Event<Photo>>()
    private var photos = mutableListOf<Photo>()


    fun loadData(albumId: Int) {
        compositeDisposable += dataSource.getPhotos(albumId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.value?.let {
                    val newState = albumDetailsViewState.copy(showLoading = true)
                    viewState.value = newState
                }
            }
            .doOnTerminate {
                viewState.value?.let {
                    val newState = albumDetailsViewState.copy(showLoading = false)
                    viewState.value = newState
                }
            }
            .subscribeBy (onError = { error ->
            }, onNext = { result ->
                result.body()?.let {body ->
                    photos = body
                    viewState.value?.let {
                        val newState = albumDetailsViewState.copy(showLoading = false, results = body)
                        viewState.value = newState
                    }
                }
            })
    }


    fun listItemClicked(item : Photo) {
        previewPhotoState.value = Event(item)
    }
}