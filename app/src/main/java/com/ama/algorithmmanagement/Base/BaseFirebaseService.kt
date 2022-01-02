package com.ama.algorithmmanagement.Base

import com.ama.algorithmmanagement.Model.*
import kotlinx.coroutines.flow.Flow

interface BaseFirebaseService {

    suspend fun setUserInfo(userId: String, userPw: String, fcmToken: String?) :Boolean

    suspend fun getUserInfo(userId: String): UserInfo?

    suspend fun checkUserInfo(userId: String, password: String): Boolean

    fun setDateInfo(userId: String): Boolean

    fun getDateInfos(userId: String?): DateInfoObject?

    suspend fun setIdeaInfo(userId: String, url: String?, comment: String?, problemId: Int): Boolean

    suspend fun getIdeaInfos(userId: String, problemId: Int): Flow<IdeaInfos?>

    fun setComment(userId: String, tierType: Int, problemId: Int, comment: String): CommentInfo

    fun getCommentObject(problemId: Int): CommentObject?

    fun setChildComment(
        userId: String,
        tierType: Int,
        commentId: String,
        comment: String
    ): ChildCommentInfo

    fun getChildCommentObject(commentId: String?): ChildCommentObject?

    fun setTippingProblem(
        userId: String,
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): TipProblem


    fun getTippingProblemObject(userId: String): TippingProblemObject?

    fun getNotTippingProblemObject(userId:String) : TippingProblemObject?

    fun modifyTippingProblem(
        userId: String,
        problemId: Int,
        isShow: Boolean?,
        comment: String?
    ): Boolean

    fun deleteTippingProblem(userId: String?, problemId: Int): Boolean

}