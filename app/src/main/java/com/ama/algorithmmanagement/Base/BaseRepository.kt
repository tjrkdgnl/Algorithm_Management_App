package com.ama.algorithmmanagement.Base

import com.ama.algorithmmanagement.Model.*
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    suspend fun getProblem(problemId: Int): TaggedProblem

    suspend fun getSolvedProblems(): Problems

    suspend fun getSearchProblemList(problemId: Int): Problems

    suspend fun getUserStats(): List<Stats>

    suspend fun getUnSolvedProblems(solvedacToken: String?): List<ProblemStatus>

    suspend fun setUserInfo(userId: String, password: String, fcmToken: String? = ""): Boolean

    suspend fun checkUserInfo(userId: String, password: String): Boolean

    suspend fun getUserInfo(): UserInfo?

    fun setDateInfo(): Boolean

    fun getDateInfoObject(): DateInfoObject?

    suspend fun setIdeaInfo(url: String?, comment: String?, problemId: Int): Boolean

    suspend fun getIdeaInfos(problemId: Int): Flow<IdeaInfos?>

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

    fun getNotTippingProblem(): TippingProblemObject?

    fun modifyTippingProblem(
        problemId: Int,
        isShow: Boolean,
        tipComment: String?
    ): Boolean

    fun deleteTippingProblem(problemId: Int): Boolean
}