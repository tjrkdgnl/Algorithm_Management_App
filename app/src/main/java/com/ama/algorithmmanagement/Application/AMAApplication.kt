package com.ama.algorithmmanagement.Application

import android.app.Application
import com.ama.algorithmmanagement.utils.SharedPrefUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class AMAApplication : Application() {
    lateinit var sharedPrefUtils: SharedPrefUtils

    override fun onCreate() {
        super.onCreate()
        sharedPrefUtils = SharedPrefUtils(this@AMAApplication)

        CoroutineScope(Dispatchers.Default).launch {
            Timber.plant(Timber.DebugTree())
        }
    }
}