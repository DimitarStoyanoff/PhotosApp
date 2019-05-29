package com.stoyanoff.kingcrimson.presentation.common

/**
 * Created by L on 27/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.stoyanoff.kingcrimson.util.Logger
import androidx.fragment.app.Fragment

abstract class BaseViewFragment : Fragment() {

    companion object {
        const val TAG = "BaseViewFragment"
        private const val FOCUS_DELAY_MILLIS = 50L
    }

    protected lateinit var fragmentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModelStates()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    abstract fun initUi()

    abstract fun initViewModelStates()

    /**
     * Sometimes during animation between fragments,
     * the user can click on an clickListener that is not handled
     * by the Navigation.
     */
    protected fun navigateTo(@IdRes resId: Int? = null, action: NavDirections? = null) {
        if (resId != null) {
            try {
                Navigation.findNavController(fragmentView).navigate(resId)
            } catch (e: IllegalArgumentException) {
            }
        }

        if (action != null) {
            try {
                findNavController().navigate(action)
            } catch (e: IllegalArgumentException) {
                Logger.e(TAG, { "Clicked on clickListener not handled by the Navigation. Ignore." }, e)
            }
        }
    }

    /**
     * Sometimes during animation between fragments,
     * the user can click on an clickListener that is not handled
     * by the Navigation.
     */
    protected fun popBackStack() {
        try {
            Navigation.findNavController(fragmentView).popBackStack()
        } catch (e: IllegalArgumentException) {
            Logger.e(TAG, { "Clicked on clickListener not handled by the Navigation. Ignore." }, e)
        }
    }

    protected fun hideSoftInput() {
        var view = activity?.currentFocus
        if (view == null) view = View(activity)
        val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun showSoftInput(view: View) {
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager).apply {
            this?.showSoftInput(view, 0)
        }
    }

    abstract fun toggleLoading(isVisible: Boolean)

    protected fun requestFocus(view: View) {
        view.postDelayed({
            view.requestFocus()
        }, FOCUS_DELAY_MILLIS)
        showSoftInput(view)
    }

    fun getStatusBarHeight(): Int {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }
}