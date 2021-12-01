package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Base.BaseSharedPreference

class FakeSharedPreference : BaseSharedPreference {
    private var mUserId: String? = null

    override fun getUserIdFromLocal(): String? {
        return mUserId
    }

    override fun delete() {
        mUserId = null
    }

    override fun setUserIdToLocal(userId: String) {
        mUserId = userId
    }


}