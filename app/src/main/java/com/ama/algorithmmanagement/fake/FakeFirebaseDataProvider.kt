package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Model.UserInfo

class FakeFirebaseDataProvider {
    val userSnapShot: MutableList<UserInfo> by lazy {
        mutableListOf()
    }


}