package com.ama.algorithmmanagement.Base

import com.ama.algorithmmanagement.Model.*

interface BaseRepository {

    suspend fun getSolvedProblems(userId: String): SolvedAlgorithms

    fun setUserInfo(userId: String, password: String, fcmToken: String? = null)

    fun checkUserInfo(userId: String, password: String): Boolean

    fun getuserInfo(): UserInfo?

    fun setDateInfo() :Result<Boolean>

    fun getDateInfoObject(): DateInfoObject?

    fun setIdeaInfo(url: String?, comment: String?, problemId: Int) :Result<Boolean>

    fun getIdeaInfos(problemId: Int): IdeaInfos?

    fun setComment(problemId: Int, comment: String): Result<Boolean>

    fun getCommentObject(problemId: Int): CommentObject?

    fun setChildComment(commentId: String?, comment: String): Result<Boolean>

    fun getChildCommentObject(commentId: String?): ChildCommentObject?

    fun setTippingProblem(problem: TaggedProblem, isShow: Boolean, tipComment: String?): Result<Boolean>

    fun getTippingProblem(): TippingProblemObject?
}