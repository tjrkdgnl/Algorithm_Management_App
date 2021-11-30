package com.ama.algorithmmanagement.utils

import android.content.Context
import com.ama.algorithmmanagement.R
import timber.log.Timber

class SharedPrefUtils(private val mContext: Context) {
    private val mSharedPreferences = mContext.getSharedPreferences("AMA_pref", Context.MODE_PRIVATE)


    fun getUserId(): String? {
        Timber.e(mSharedPreferences.getString(mContext.getString(R.string.prefGetUserId), null))
        return mSharedPreferences.getString(mContext.getString(R.string.prefGetUserId), null)
    }

    fun delete() {
        with(mSharedPreferences.edit()) {
            remove(mContext.getString(R.string.prefGetUserId))
            commit()
        }
    }

    fun setUserId(userId: String) {
        with(mSharedPreferences.edit()) {
            putString(mContext.getString(R.string.prefGetUserId), userId)
            commit()
        }
    }
}