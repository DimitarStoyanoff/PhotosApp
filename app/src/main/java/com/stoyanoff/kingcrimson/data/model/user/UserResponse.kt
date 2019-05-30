package com.stoyanoff.kingcrimson.data.model.user

import com.google.gson.annotations.SerializedName

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
data class UserResponse (
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("username")
    val userName: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("address")
    val address: Address?,

    @SerializedName("phone")
    val phone: String?,

    @SerializedName("website")
    val website: String?,

    @SerializedName("company")
    val company: Company?
)