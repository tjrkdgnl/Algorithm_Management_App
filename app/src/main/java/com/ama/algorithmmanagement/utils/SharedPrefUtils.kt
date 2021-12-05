package com.ama.algorithmmanagement.utils

import android.content.Context
import com.ama.algorithmmanagement.Base.BaseSharedPreference
import com.ama.algorithmmanagement.R
import timber.log.Timber

class SharedPrefUtils(private val mContext: Context) : BaseSharedPreference {
    private val mSharedPreferences = mContext.getSharedPreferences("AMA_pref", Context.MODE_PRIVATE)


    override fun getUserIdFromLocal(): String? {
        Timber.e(mSharedPreferences.getString(mContext.getString(R.string.prefGetUserId), null))
        return mSharedPreferences.getString(mContext.getString(R.string.prefGetUserId), null)
    }

    override fun deleteToUserId() {
        with(mSharedPreferences.edit()) {
            remove(mContext.getString(R.string.prefGetUserId))
            commit()
        }
    }

    override fun setUserIdToLocal(userId: String) {
        with(mSharedPreferences.edit()) {
            putString(mContext.getString(R.string.prefGetUserId), userId)
            commit()
        }
    }

    override fun setTierType(tierType: Int) {
        TODO("Not yet implemented")
    }

    override fun getTierType(): Int? {
        TODO("Not yet implemented")
    }

    override fun deleteToTierType() {
        TODO("Not yet implemented")
    }
}