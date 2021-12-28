package com.ama.algorithmmanagement.Base

import com.ama.algorithmmanagement.Model.*

interface BaseRepository {

    suspend fun getSolvedProblems(): Problems

    suspend fun getSearchProblemList(problemId: Int): Problems

    suspend fun getUserStats(): List<Stats>

    suspend fun getBOJUserInfo(): List<ProblemStatus>

    suspend fun setUserInfo(userId: String, password: String, fcmToken: String? = null)

    suspend fun checkUserInfo(userId: String, password: String): Boolean

    suspend fun getUserInfo(): UserInfo?

    fun setDateInfo(): Boolean

    fun getDateInfoObject(): DateInfoObject?

    fun setIdeaInfo(url: String?, comment: String?, problemId: Int): IdeaInfo

    fun getIdeaInfos(problemId: Int): IdeaInfos?

    fun setComment(problemId: Int, comment: String): CommentInfo

    fun getCommentObject(problemId: Int): CommentObject?

    fun setChildComment(commentId: String, comment: String): ChildCommentInfo

    fun getChildCommentObject(commentId: String): ChildCommentObject?

    fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): TipProblem

    fun getTippingProblem(): TippingProblemObject?

    fun modifyTippingProblem(
        problemId: Int,
        isShow: Boolean?,
        comment: String?
    ): Boolean

    fun deleteTippingProblem(problemId: Int): Boolean
}