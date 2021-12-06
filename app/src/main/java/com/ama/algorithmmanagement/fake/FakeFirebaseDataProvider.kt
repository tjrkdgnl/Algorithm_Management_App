package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Model.*

class FakeFirebaseDataProvider {
    val userSnapShot: MutableList<UserInfo> by lazy {
        mutableListOf()
    }

    val dateSnapShot: MutableList<DateInfos> by lazy {
        mutableListOf()
    }

    val ideaSnapShot: MutableList<IdeaObject> by lazy {
        mutableListOf()
    }

    val commentSnapShot: MutableList<CommentObject> by lazy {
        mutableListOf()
    }

    val childCommentSanpShot: MutableList<ChildCommentObject> by lazy {
        mutableListOf()
    }

}