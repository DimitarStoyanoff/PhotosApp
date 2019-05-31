package com.stoyanoff.kingcrimson.data.model.post

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
@Parcelize
data class Post (
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("body")
    val body: String?
) : Parcelable