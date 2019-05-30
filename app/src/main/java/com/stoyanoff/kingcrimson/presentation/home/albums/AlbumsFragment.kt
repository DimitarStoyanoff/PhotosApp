package com.stoyanoff.kingcrimson.presentation.home.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.presentation.common.BaseViewFragment
import kotlinx.android.synthetic.main.fragment_albums.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class AlbumsFragment : BaseViewFragment() {

    val viewModel: AlbumsViewModel by viewModel()
    private val albumsAdapter: AlbumsAdapter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_albums,container,false)

        return fragmentView
    }

    override fun initViewModelStates() {
        handleAlbumsViewState()
        handleNavigateToDetailsEvent()
    }

    override fun toggleLoading(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
        } else progressBar.visibility = View.GONE
    }

    override fun initUi() {
        initAdapter()
        loadData()
    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun initAdapter() {
        albumsAdapter.clickListener = {
            viewModel.listItemClicked(it)
        }

        with(recycler_view){
            layoutManager = LinearLayoutManager(activity)
            adapter = albumsAdapter
        }
    }

    private fun handleAlbumsViewState() {
        viewModel.viewState.observe(this, Observer {
            if(it != null) {
                toggleLoading(it.showLoading)

                it.results?.let {albums ->
                    albumsAdapter.setItems(albums)
                }
            }
        })
    }

    private fun handleNavigateToDetailsEvent() {
        viewModel.navigateToAlbumDetails.observe(this, Observer {
            //TODO navigate
        })
    }

}