package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Base.BaseSharedPreference

class FakeSharedPreference : BaseSharedPreference {
    private var mUserId: String? = null
    private var mTierType: Int? = null
    private var mSolvedacToken :String? =null

    override fun getUserIdFromLocal(): String? {
        return mUserId
    }

    override fun deleteToUserId() {
        mUserId = null
    }

    override fun setUserIdToLocal(userId: String) {
        mUserId = userId
    }

    override fun setTierType(tierType: Int) {
        mTierType = tierType
    }

    override fun getTierType(): Int? {
        return mTierType
    }

    override fun deleteToTierType() {
        mTierType = null
    }

    override fun setSolvedacToken(solvedacToken: String) {
        mSolvedacToken = solvedacToken
    }

    override fun getSolvedacToken(): String? {
        return mSolvedacToken
    }


}