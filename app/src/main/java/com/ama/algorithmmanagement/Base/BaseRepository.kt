package com.ama.algorithmmanagement.Base

import com.ama.algorithmmanagement.Model.*
import kotlinx.coroutines.delay

interface BaseRepository {

    suspend fun getSolvedProblems(userId: String): Problems

    suspend fun getSearchProblemList(problemId: String): Problems

    suspend fun getUserStats(userId: String): List<Stats>

    suspend fun getBOJUserInfo(): List<ProblemStatus>

    fun setUserInfo(userId: String, password: String, fcmToken: String? = null)

    fun checkUserInfo(userId: String, password: String): Boolean

    fun getuserInfo(): UserInfo?

    fun setDateInfo(): Result<Boolean>

    fun getDateInfoObject(): DateInfoObject?

    fun setIdeaInfo(url: String?, comment: String?, problemId: Int): Result<Boolean>

    fun getIdeaInfos(problemId: Int): IdeaInfos?

    fun setComment(problemId: Int, comment: String): Result<CommentInfo>

    fun getCommentObject(problemId: Int): CommentObject?

    fun setChildComment(commentId: String, comment: String): Result<ChildCommentInfo>

    fun getChildCommentObject(commentId: String): ChildCommentObject?

    fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): Result<Boolean>

    fun getTippingProblem(): TippingProblemObject?
}