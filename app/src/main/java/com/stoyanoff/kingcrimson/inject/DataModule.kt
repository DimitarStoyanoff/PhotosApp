package com.stoyanoff.kingcrimson.inject

import com.stoyanoff.kingcrimson.presentation.home.albums.AlbumsDataSource
import com.stoyanoff.kingcrimson.presentation.home.albums.AlbumsRepository
import com.stoyanoff.kingcrimson.presentation.home.albums.albumdetails.AlbumDetailsDataSource
import com.stoyanoff.kingcrimson.presentation.home.albums.albumdetails.AlbumDetailsRepository
import com.stoyanoff.kingcrimson.presentation.home.posts.PostsDataSource
import com.stoyanoff.kingcrimson.presentation.home.posts.PostsRepository
import com.stoyanoff.kingcrimson.presentation.home.profile.ProfileDataSource
import com.stoyanoff.kingcrimson.presentation.home.profile.ProfileRepository
import com.stoyanoff.kingcrimson.presentation.launch.login.LoginDataSource
import com.stoyanoff.kingcrimson.presentation.launch.login.LoginRepository
import org.koin.dsl.module.module

/**
 * Created by L on 29/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
val dataModule = module {

    factory<LoginDataSource> { LoginRepository(get()) }
    factory<AlbumsDataSource> { AlbumsRepository(get()) }
    factory<AlbumDetailsDataSource> {AlbumDetailsRepository(get())}
    factory<PostsDataSource> {PostsRepository(get())}
    factory<ProfileDataSource> {ProfileRepository(get())}
}