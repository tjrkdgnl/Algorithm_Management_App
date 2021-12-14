package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.Network.KAPIGenerator
import com.ama.algorithmmanagement.Network.BaseNetworkService

class Repository : BaseRepository {

    private val networkService = object : BaseNetworkService {
        override suspend fun getSolvedProblems(userId: String): Problems {
            return KAPIGenerator.getInstance().getProblems(userId)
        }

        override suspend fun getUserStats(userId: String): List<Stats> {
            TODO("Not yet implemented")
        }

        override suspend fun getBOJUserInfo(): List<ProblemStatus> {
            TODO("Not yet implemented")
        }

        override suspend fun getSearchProblemList(problemId: Int): Problems {
            TODO("Not yet implemented")
        }

    }


    override suspend fun getSolvedProblems(userId: String): Problems {
        return networkService.getSolvedProblems(userId)
    }

    override suspend fun getSearchProblemList(problemId: String): Problems {
        TODO("Not yet implemented")
    }

    override suspend fun getUserStats(userId: String): List<Stats> {
        TODO("Not yet implemented")
    }

    override suspend fun getBOJUserInfo(): List<ProblemStatus> {
        TODO("Not yet implemented")
    }

    override fun setUserInfo(userId: String, password: String, fcmToken: String?) {
        TODO("Not yet implemented")
    }

    override fun checkUserInfo(userId: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getuserInfo(): UserInfo? {
        TODO("Not yet implemented")
    }

    override fun setDateInfo(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getDateInfoObject(): DateInfoObject? {
        TODO("Not yet implemented")
    }

    override fun setIdeaInfo(url: String?, comment: String?, problemId: Int): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getIdeaInfos(problemId: Int): IdeaInfos? {
        TODO("Not yet implemented")
    }

    override fun setComment(problemId: Int, comment: String): Result<CommentInfo> {
        TODO("Not yet implemented")
    }

    override fun getCommentObject(problemId: Int): CommentObject? {
        TODO("Not yet implemented")
    }

    override fun setChildComment(commentId: String, comment: String): Result<ChildCommentInfo> {
        TODO("Not yet implemented")
    }

    override fun getChildCommentObject(commentId: String): ChildCommentObject? {
        TODO("Not yet implemented")
    }

    override fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getTippingProblem(): TippingProblemObject? {
        TODO("Not yet implemented")
    }
}