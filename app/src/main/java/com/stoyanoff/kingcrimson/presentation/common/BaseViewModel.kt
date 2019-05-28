package com.stoyanoff.kingcrimson.presentation.common

/**
 * Created by L on 27/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()

        this.disposeDisposables()
    }

    private fun disposeDisposables() {
        if (!this.compositeDisposable.isDisposed) {
            this.compositeDisposable.dispose()
        }
    }
}