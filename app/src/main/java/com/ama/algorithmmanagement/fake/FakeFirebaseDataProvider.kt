package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Model.DateInfos
import com.ama.algorithmmanagement.Model.UserIdeaInfo
import com.ama.algorithmmanagement.Model.UserInfo

class FakeFirebaseDataProvider {
    val userSnapShot: MutableList<UserInfo> by lazy {
        mutableListOf()
    }

    val dateSnapShot: MutableList<DateInfos> by lazy {
        mutableListOf()
    }

    val ideaSnapShot: MutableList<UserIdeaInfo> by lazy {
        mutableListOf()
    }

}