package com.stoyanoff.kingcrimson.inject

import com.stoyanoff.kingcrimson.presentation.home.albums.AlbumsAdapter
import com.stoyanoff.kingcrimson.presentation.home.albums.AlbumsViewModel
import com.stoyanoff.kingcrimson.presentation.home.albums.AlbumsViewState
import com.stoyanoff.kingcrimson.presentation.home.albums.albumdetails.AlbumDetailsViewModel
import com.stoyanoff.kingcrimson.presentation.home.albums.albumdetails.AlbumDetailsViewState
import com.stoyanoff.kingcrimson.presentation.home.albums.albumdetails.PhotosAdapter
import com.stoyanoff.kingcrimson.presentation.home.posts.PostsAdapter
import com.stoyanoff.kingcrimson.presentation.home.posts.PostsViewModel
import com.stoyanoff.kingcrimson.presentation.home.posts.PostsViewState
import com.stoyanoff.kingcrimson.presentation.home.posts.addpost.AddPostViewModel
import com.stoyanoff.kingcrimson.presentation.home.posts.addpost.AddPostViewState
import com.stoyanoff.kingcrimson.presentation.home.profile.ProfileViewModel
import com.stoyanoff.kingcrimson.presentation.home.profile.ProfileViewState
import com.stoyanoff.kingcrimson.presentation.launch.login.LoginViewModel
import com.stoyanoff.kingcrimson.presentation.launch.login.LoginViewState
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
val presentationModule = module {

    viewModel { LoginViewModel(get(), get()) }
    factory { LoginViewState() }

    viewModel { ProfileViewModel(get(), get()) }
    factory { ProfileViewState() }

    viewModel { AlbumsViewModel(get(), get()) }
    factory { AlbumsViewState() }
    factory { AlbumsAdapter() }

    viewModel { AlbumDetailsViewModel(get(), get()) }
    factory { AlbumDetailsViewState() }
    factory { PhotosAdapter() }

    viewModel { PostsViewModel(get(), get()) }
    factory { PostsViewState() }
    factory { PostsAdapter() }

    viewModel { AddPostViewModel(get(), get()) }
    factory { AddPostViewState() }
}