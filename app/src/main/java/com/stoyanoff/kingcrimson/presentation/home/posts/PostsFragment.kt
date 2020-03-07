package com.stoyanoff.kingcrimson.presentation.home.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.data.remote.Status
import com.stoyanoff.kingcrimson.presentation.common.BaseViewFragment
import kotlinx.android.synthetic.main.fragment_albums.progressBar
import kotlinx.android.synthetic.main.fragment_albums.recycler_view
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by L on 30/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class PostsFragment : BaseViewFragment() {

    val viewModel: PostsViewModel by viewModel()
    private val postsAdapter: PostsAdapter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_posts,container,false)

        return fragmentView
    }

    override fun initViewModelStates() {
        handlePostsViewState()
        handleNavigateToAddPost()

    }

    override fun toggleLoading(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
        } else progressBar.visibility = View.GONE
    }

    override fun initUi() {
        initAdapter()
        loadData()
        initAddButton()
    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun initAdapter() {
        postsAdapter.deleteClickListener = {
            viewModel.listItemDeleteClicked(it)
        }

        postsAdapter.editClickListener = {
            viewModel.listItemEditClicked(it)
        }

        with(recycler_view){
            layoutManager = LinearLayoutManager(activity)
            adapter = postsAdapter
        }
    }

    private fun handlePostsViewState() {
        viewModel.viewState.observe(this, Observer {
            if(it != null) {
                toggleLoading(it.showLoading)

                it.data?.let {posts ->
                    postsAdapter.setItems(posts)
                }
            }
        })

        viewModel.postsResponseLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    toggleLoading(false)
                    it.data?.let {posts ->
                        postsAdapter.setItems(posts)
                    }
                }
                Status.ERROR -> {
                    toggleLoading(false)
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> toggleLoading(true)
            }
        })
    }

    private fun initAddButton() {
        add_image_view.setOnClickListener {
            viewModel.addButtonClicked()
        }
    }

    private fun handleNavigateToAddPost() {
        viewModel.navigateToAddPost.observe(this, Observer {
            it?.let {
                navigateTo(R.id.action_postsFragment_to_addPostFragment)
            }
        })
    }

}