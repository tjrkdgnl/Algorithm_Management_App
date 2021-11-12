package com.ama.algorithmmanagement.utils

import android.content.Context
import com.ama.algorithmmanagement.R

class SharedPrefUtils(private val mContext: Context) {
    private val mSharedPreferences = mContext.getSharedPreferences("AMA_pref", Context.MODE_PRIVATE)


    fun getUserId(): String? {
        return mSharedPreferences.getString(mContext.getString(R.string.prefGetUserId), null)
    }

    fun setUserId(userId: String) {
        with(mSharedPreferences.edit()) {
            putString(mContext.getString(R.string.prefGetUserId), userId)
            commit()
        }
    }
}