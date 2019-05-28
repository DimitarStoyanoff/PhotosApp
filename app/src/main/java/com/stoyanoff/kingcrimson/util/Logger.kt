package com.stoyanoff.kingcrimson.util

/**
 * Created by L on 27/05/2019.
 *  Copyright (c) 2017 Centroida. All rights reserved.
 */

import android.os.Build
import android.util.Log
import com.stoyanoff.kingcrimson.BuildConfig
import java.util.regex.Pattern

/**
 * A logger to avoid logging in production. It will check if [BuildConfig.DEBUG] is true or not.
 * If true then allow logging, otherwise don't.
 *
 * All log strings are initialized lazily with a closure to keep the garbage to one object only.
 */

object Logger {

    fun v(tag: String = callerTag(), message: () -> String) = inDebug {
        Log.v(tag, message())
    }

    fun v(tag: String = callerTag(), message: () -> String, exception: Exception) = inDebug {
        Log.v(tag, message(), exception)
    }

    fun d(tag: String = callerTag(), message: () -> String) = inDebug {
        Log.d(tag, message())
    }

    fun d(tag: String = callerTag(), message: () -> String, exception: Exception) = inDebug {
        Log.d(tag, message(), exception)
    }

    fun i(tag: String = callerTag(), message: () -> String) = inDebug {
        Log.i(tag, message())
    }

    fun i(tag: String = callerTag(), message: () -> String, exception: Exception) = inDebug {
        Log.i(tag, message(), exception)
    }

    fun w(tag: String = callerTag(), message: () -> String) = inDebug {
        Log.w(tag, message())
    }

    fun w(tag: String = callerTag(), message: () -> String, exception: Exception) = inDebug {
        Log.w(tag, message(), exception)
    }

    fun e(tag: String = callerTag(), message: () -> String?) = inDebug {
        Log.e(tag, message())
    }

    fun e(tag: String = callerTag(), message: () -> String, exception: Exception?) = inDebug {
        Log.e(tag, message(), exception)
    }

    fun e(tag: String = callerTag(), message: () -> String, throwable: Throwable?) = inDebug {
        Log.e(tag, message(), throwable)
    }

    fun wtf(tag: String = callerTag(), message: () -> String) = inDebug {
        Log.wtf(tag, message())
    }

    fun wtf(tag: String = callerTag(), message: () -> String, exception: Exception) = inDebug {
        Log.wtf(tag, message(), exception)
    }

    private inline fun inDebug(action: () -> Unit) {
        if (BuildConfig.DEBUG) {
            action()
        }
    }

    /**
     * @return The class name for the calling class as a String.
     */
    private fun callerTag(): String {
        val callStackIndex = 2
        val maxTagLength = 23
        val anonymousClassPattern = Pattern.compile("(\\$\\d+)+$")

        val stackTrace = Throwable().stackTrace
        val callerElement = stackTrace[callStackIndex]
        var tag = callerElement.className
        val matcher = anonymousClassPattern.matcher(tag)

        if (matcher.find()) {
            tag = matcher.replaceAll("")
        }

        tag = tag.substring(tag.lastIndexOf('.') + 1)

        // Tag length limit was removed in API 24.
        return if (tag.length <= maxTagLength || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tag
        } else tag.substring(0, maxTagLength)
    }
}