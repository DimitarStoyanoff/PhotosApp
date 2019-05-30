package com.stoyanoff.kingcrimson.presentation.launch.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.stoyanoff.kingcrimson.R
import com.stoyanoff.kingcrimson.presentation.launch.LaunchActivity
import com.stoyanoff.kingcrimson.presentation.common.BaseViewFragment
import com.stoyanoff.kingcrimson.presentation.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by L on 28/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */
class LoginFragment : BaseViewFragment() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false)


        return fragmentView
    }

    override fun toggleLoading(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
        } else progressBar.visibility = View.GONE
    }

    override fun initUi() {
        initLoginButton()
        initUserIdField()
    }

    override fun initViewModelStates() {
        handleLoginViewState()
        handleShowErrorMessageEvent()
        handleNavigateToHomeEvent()
    }

    private fun initUserIdField() {
        user_id_et.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //TODO input validation
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun handleLoginViewState() {
        viewModel.viewState.observe(this, Observer {loginViewState ->
            if(loginViewState != null) {
                toggleLoading(loginViewState.showLoading)
            }
        })
    }

    private fun handleShowErrorMessageEvent() {
        viewModel.showErrorMessageEvent.observe(this, Observer {messageEvent ->
            messageEvent.getContentIfNotHandled()?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleNavigateToHomeEvent() {
        viewModel.navigateToHomeEvent.observe(this, Observer {event ->
            event.getContentIfNotHandled()?.let {
                val intent = Intent(context, HomeActivity::class.java)
                    .apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                startActivity(intent)
                activity?.finish()
            }

        })
    }

    private fun initLoginButton() {
        login_button.setOnClickListener {
            viewModel.onLoginClicked((user_id_et.text.toString()))
        }
    }

}