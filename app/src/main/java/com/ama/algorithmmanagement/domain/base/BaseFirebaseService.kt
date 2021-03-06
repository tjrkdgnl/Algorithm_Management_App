package com.ama.algorithmmanagement.domain.base

import com.ama.algorithmmanagement.data.firebase.SortEnum
import com.ama.algorithmmanagement.domain.entity.*
import kotlinx.coroutines.flow.Flow

interface BaseFirebaseService {

    suspend fun setUserInfo(userId: String, userPw: String, fcmToken: String?,solvedToken:String): Boolean

    suspend fun getUserInfo(userId: String): UserInfo?

    suspend fun signUpUserInfo(userId: String, password: String): Boolean

    suspend fun setDateInfo(userId: String, count: Int): Boolean

    suspend fun getDateObject(userId: String?): DateObject?

    suspend fun setIdeaInfo(userId: String, url: String?, comment: String?, problemId: Int): Boolean

    suspend fun getIdeaInfos(userId: String, problemId: Int): Flow<IdeaInfos?>

    suspend fun setComment(userId: String, tierType: Int, problemId: Int, comment: String): Boolean

    suspend fun getCommentObject(problemId: Int): CommentObject?

    suspend fun setChildComment(
        problemId: Int,
        userId: String,
        tierType: Int,
        commentId: String,
        comment: String
    ): Boolean

    suspend fun getChildCommentObject(commentId: String?): ChildCommentObject?

    suspend fun setTippingProblem(
        userId: String,
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): Boolean

    suspend fun initTipProblems(
        userId: String,
        problems: List<TaggedProblem>
    ): TippingProblemObject?

    suspend fun getAllTipProblems(userId: String): TippingProblemObject?

    suspend fun getTippingProblemObject(userId: String): TippingProblemObject?

    suspend fun getNotTippingProblemObject(userId: String): TippingProblemObject?

    suspend fun modifyTippingProblem(
        userId: String,
        problemId: Int,
        isShow: Boolean,
        comment: String?
    ): Boolean

    suspend fun deleteTippingProblem(userId: String?, problemId: Int): Boolean

}