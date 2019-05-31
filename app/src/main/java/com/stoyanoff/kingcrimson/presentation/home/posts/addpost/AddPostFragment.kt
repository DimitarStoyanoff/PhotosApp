package com.stoyanoff.kingcrimson.presentation.home.posts.addpost

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.presentation.common.BaseViewFragment
import kotlinx.android.synthetic.main.fragment_add_post.*
import kotlinx.android.synthetic.main.fragment_albums.*
import kotlinx.android.synthetic.main.fragment_albums.progressBar
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by L on 31/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class AddPostFragment : BaseViewFragment() {

    private val viewModel : AddPostViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_add_post,container,false)
        return fragmentView
    }

    override fun initUi() {
        initButtons()
        initFields()
    }

    private fun initButtons() {
        button_post.setOnClickListener {
            viewModel.postButtonClicked(post_title_edit_text.text.toString(),post_body_edit_text.text.toString())
        }
        toggleSendButton(false)
    }

    private fun initFields() {
        post_title_edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                toggleSendButton(p0?.isNotEmpty() ?: false && post_body_edit_text.text.toString().isNotEmpty())
                //TODO extract validation logic
                }

        })

        post_body_edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                toggleSendButton(p0?.isNotEmpty() ?: false && post_title_edit_text.text.toString().isNotEmpty())
            }

        })
    }

    //TODO move responsibility to viewmodel, disable when request is processed
    private fun toggleSendButton(isClickable : Boolean) {
        button_post.isClickable = isClickable
    }

    override fun initViewModelStates() {
        handleShowErrorMessageEvent()
        handleNavigateBackEvent()
        handleViewState()
    }

    private fun handleViewState() {
        viewModel.viewState.observe(this, Observer {
            toggleLoading(it.showLoading)
        })
    }

    override fun toggleLoading(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
        } else progressBar.visibility = View.GONE
    }

    private fun handleShowErrorMessageEvent() {
        viewModel.showMessageEvent.observe(this, Observer {messageEvent ->
            messageEvent.getContentIfNotHandled()?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleNavigateBackEvent() {
        viewModel.navigateBackEvent.observe(this, Observer {
            it.getContentIfNotHandled()?.let { outcome ->
                if(outcome)
                    popBackStack()
            }
        })
    }

}