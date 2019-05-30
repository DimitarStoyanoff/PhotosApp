package com.stoyanoff.kingcrimson.presentation.home.albums.albumdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.data.model.album.Album
import com.stoyanoff.kingcrimson.presentation.common.BaseViewFragment
import kotlinx.android.synthetic.main.fragment_album_details.*
import kotlinx.android.synthetic.main.fragment_album_details.progressBar
import kotlinx.android.synthetic.main.fragment_album_details.recycler_view
import kotlinx.android.synthetic.main.fragment_albums.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class AlbumDetailsFragment : BaseViewFragment() {

    val viewModel : AlbumDetailsViewModel by viewModel()
    private val photosAdapter : PhotosAdapter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_album_details,container,false)

        handleBundle()
        return fragmentView
    }

    private fun handleBundle() {
        with(AlbumDetailsFragmentArgs.fromBundle(arguments)) {
            albumdata.id?.let {
                viewModel.loadData(it)
            }
        }
    }

    override fun initUi() {
        initAdapter()
    }

    private fun initAdapter() {
        photosAdapter.clickListener = {
            viewModel.listItemClicked(it)
        }

        with(recycler_view){
            layoutManager = GridLayoutManager(activity,2)
            adapter = photosAdapter
        }
    }

    override fun initViewModelStates() {
        handlePhotosViewState()
    }

    private fun handlePhotosViewState() {
        viewModel.viewState.observe(this, Observer {
            if(it != null) {
                toggleLoading(it.showLoading)

                it.results?.let {photos ->
                    photosAdapter.setItems(photos)
                }
            }
        })
    }

    override fun toggleLoading(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
        } else progressBar.visibility = View.GONE
    }
}