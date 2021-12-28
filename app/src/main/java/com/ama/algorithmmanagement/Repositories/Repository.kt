package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.Network.BaseNetworkService
import com.ama.algorithmmanagement.Network.KAPIGenerator

class Repository : BaseRepository {

    override suspend fun getProblem(problemId: Int): TaggedProblem {
        TODO("Not yet implemented")
    }

    override suspend fun getSolvedProblems(): Problems {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchProblemList(problemId: Int): Problems {
        TODO("Not yet implemented")
    }

    override suspend fun getUserStats(): List<Stats> {
        TODO("Not yet implemented")
    }

    override suspend fun getUnSolvedProblems(solvedacToken: String?): List<ProblemStatus> {
        TODO("Not yet implemented")
    }

    override suspend fun setUserInfo(userId: String, password: String, fcmToken: String?): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun checkUserInfo(userId: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfo(): UserInfo? {
        TODO("Not yet implemented")
    }

    override fun setDateInfo(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDateInfoObject(): DateInfoObject? {
        TODO("Not yet implemented")
    }

    override fun setIdeaInfo(url: String?, comment: String?, problemId: Int): IdeaInfo {
        TODO("Not yet implemented")
    }

    override fun getIdeaInfos(problemId: Int): IdeaInfos? {
        TODO("Not yet implemented")
    }

    override fun setComment(problemId: Int, comment: String): CommentInfo {
        TODO("Not yet implemented")
    }

    override fun getCommentObject(problemId: Int): CommentObject? {
        TODO("Not yet implemented")
    }

    override fun setChildComment(commentId: String, comment: String): ChildCommentInfo {
        TODO("Not yet implemented")
    }

    override fun getChildCommentObject(commentId: String): ChildCommentObject? {
        TODO("Not yet implemented")
    }

    override fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): TipProblem {
        TODO("Not yet implemented")
    }

    override fun getTippingProblem(): TippingProblemObject? {
        TODO("Not yet implemented")
    }

    override fun getNotTippingProblem(): TippingProblemObject? {
        TODO("Not yet implemented")
    }

    override fun modifyTippingProblem(
        problemId: Int,
        isShow: Boolean,
        tipComment: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteTippingProblem(problemId: Int): Boolean {
        TODO("Not yet implemented")
    }
}