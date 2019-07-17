package com.stoyanoff.kingcrimson.presentation.home.albums

import com.stoyanoff.kingcrimson.data.remote.RemoteService
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Matchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by L on 11/07/2019.
 * Copyright (c) 2017 Centroida. All rights reserved.
 */
class AlbumsRepositoryTest {

    @Mock
    private lateinit var remoteService: RemoteService

    private lateinit var repository: AlbumsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.repository = AlbumsRepository(this.remoteService)
    }

    @Test
    fun getAlbums() {
        repository.getAlbums()
        verify(remoteService).getAlbums(anyInt())
    }
}