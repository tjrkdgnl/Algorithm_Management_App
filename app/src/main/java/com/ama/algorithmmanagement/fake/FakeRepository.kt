package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.Network.BaseNetworkService

class FakeRepository(
    private val mFakeFirebaseReference: FakeFirebaseReference,
    private val mBaseNetworkService: BaseNetworkService
) : BaseRepository {

    override suspend fun getSolvedProblems(userId: String): Problems {
        return mBaseNetworkService.getSolvedProblems(userId)
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
        mFakeFirebaseReference.setUserInfo(userId, password, fcmToken)
    }

    override fun checkUserInfo(userId: String, password: String): Boolean {
        return mFakeFirebaseReference.checkUserInfo(userId, password)
    }

    override fun getuserInfo(): UserInfo? {
        return mFakeFirebaseReference.getUserInfo()
    }

    override fun setDateInfo() :Result<Boolean> {
       return mFakeFirebaseReference.setDateInfo()
    }

    override fun getDateInfoObject(): DateInfoObject? {
        return mFakeFirebaseReference.getDateInfos()
    }

    override fun setIdeaInfo(url: String?, comment: String?, problemId: Int): Result<Boolean> {
        return mFakeFirebaseReference.setIdeaInfo(url, comment, problemId)
    }

    override fun getIdeaInfos(problemId: Int): IdeaInfos? {
        return mFakeFirebaseReference.getIdeaInfos(problemId)
    }

    override fun setComment(problemId: Int, comment: String): Result<Boolean> {
        return mFakeFirebaseReference.setComment(problemId, comment)
    }

    override fun getCommentObject(problemId: Int): CommentObject? {
       return mFakeFirebaseReference.getCommentObject(problemId)
    }

    override fun setChildComment(commentId: String?, comment: String): Result<Boolean> {
        return mFakeFirebaseReference.setChildComment(commentId, comment)
    }

    override fun getChildCommentObject(commentId: String?): ChildCommentObject? {
        return mFakeFirebaseReference.getChildCommentObject(commentId)
    }

    override fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): Result<Boolean> {
        return mFakeFirebaseReference.setTippingProblem(problem, isShow, tipComment)
    }

    override fun getTippingProblem(): TippingProblemObject? {
        return mFakeFirebaseReference.getTippingProblem()
    }

}