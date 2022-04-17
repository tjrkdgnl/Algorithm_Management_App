package com.ama.algorithmmanagement.domain.base

import com.ama.algorithmmanagement.domain.entity.*
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    suspend fun getProblem(problemId: Int): TaggedProblem

    suspend fun getSolvedProblems(): Problems

    suspend fun getSearchProblemList(problemId: Int): Problems

    suspend fun getUserStats(): List<Stats>

    suspend fun getAutoSearchedData(keyword:String) : AutoKeywordObject

    suspend fun getUnSolvedProblems(solvedacToken: String?): List<ProblemStatus>

    suspend fun getBOJUserInfo() : User

    suspend fun setUserInfo(userId: String, password: String, fcmToken: String? = ""): Boolean

    suspend fun confirmUserInfo(userId: String) : Boolean

    suspend fun signUpUserInfo(userId: String, password: String): Boolean

    suspend fun getUserInfo(userId:String): UserInfo?

    suspend fun setDateInfo(count:Int): Boolean

    suspend fun getDateObject(): DateObject?

    suspend fun setIdeaInfo(url: String?, comment: String?, problemId: Int): Boolean

    suspend fun getIdeaInfos(problemId: Int): Flow<IdeaInfos?>

    suspend fun setComment(problemId: Int, comment: String): Boolean

    suspend fun getCommentObject(problemId: Int): CommentObject?

    suspend fun setChildComment(problemId: Int,commentId: String, comment: String): Boolean

    suspend fun getChildCommentObject(commentId: String): ChildCommentObject?

    suspend fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): Boolean

    suspend fun initTipProblems(problems: List<TaggedProblem>): TippingProblemObject?

    suspend fun getAllTipProblems() : TippingProblemObject?

    suspend fun getTippingProblem(): TippingProblemObject?

    suspend fun getNotTippingProblem(): TippingProblemObject?

    suspend fun modifyTippingProblem(
        problemId: Int,
        isShow: Boolean,
        tipComment: String?
    ): Boolean

    suspend fun deleteTippingProblem(problemId: Int): Boolean
}