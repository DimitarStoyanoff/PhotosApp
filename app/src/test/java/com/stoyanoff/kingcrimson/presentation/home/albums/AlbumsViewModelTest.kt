package com.stoyanoff.kingcrimson.presentation.home.albums

import com.nhaarman.mockitokotlin2.verify
import com.stoyanoff.kingcrimson.InstantTaskExecutorRule
import com.stoyanoff.kingcrimson.LiveDataTestUtil
import com.stoyanoff.kingcrimson.RxImmediateSchedulerRule
import com.stoyanoff.kingcrimson.data.model.album.Album
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

/**
 * Created by L on 12/07/2019.
 * Copyright (c) 2017 Centroida. All rights reserved.
 */
@RunWith(JUnit4::class)
class AlbumsViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var dataSource: AlbumsDataSource
    private val album = Album(1,1,"title")

    private lateinit var viewModel: AlbumsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = AlbumsViewModel(AlbumsViewState(), this.dataSource)
    }

    @Test
    fun loadData() {
        val spyViewModel = Mockito.spy(viewModel)
        val list = mutableListOf<Album>(album)
        val response = Response.success(list)
        Mockito.`when`(dataSource.getAlbums()).thenReturn(Observable.just(response))

        spyViewModel.loadData()
        verify(dataSource).getAlbums()
        verify(spyViewModel).toggleLoadingState(true)
        verify(spyViewModel).showResults(list)
        verify(spyViewModel).toggleLoadingState(false)
    }

    @Test
    fun showResults() {
        val list = mutableListOf<Album>(album)

        viewModel.showResults(list)
        val resultState = LiveDataTestUtil.getValue(viewModel.viewState)
        assertFalse(resultState.showLoading)
        assertEquals(resultState.results,list)
    }

    @Test
    fun listItemClicked() {
        viewModel.listItemClicked(album)
        assertEquals(LiveDataTestUtil.getValue(viewModel.navigateToAlbumDetails).peekContent(),album)
    }
}