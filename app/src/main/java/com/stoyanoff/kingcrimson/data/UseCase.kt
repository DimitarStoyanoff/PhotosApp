package com.stoyanoff.kingcrimson.data

import io.reactivex.Observable

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
abstract class UseCase<T> {

    abstract fun createObservable(data: Map<String, Any>? = null): Observable<T>
}