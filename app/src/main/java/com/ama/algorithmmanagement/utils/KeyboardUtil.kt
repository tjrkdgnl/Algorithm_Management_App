package com.ama.algorithmmanagement.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

object KeyboardUtil {
    fun hideKeyboard(context: Context?, activity: Activity?) {
        (context?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
        }
    }
}