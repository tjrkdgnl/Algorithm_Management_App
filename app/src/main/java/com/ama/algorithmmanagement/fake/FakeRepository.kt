package com.ama.algorithmmanagement.fake

import android.app.Application
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Base.BaseSharedPreference
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.Network.BaseNetworkService
import com.ama.algorithmmanagement.R

class FakeRepository(
    private val mApp: Application,
    private val mFakeFirebaseReference: FakeFirebaseReference,
    private val mBaseNetworkService: BaseNetworkService,
    private val mSharedPrefUtils: BaseSharedPreference,
) : BaseRepository {

    private var mUserId = mSharedPrefUtils.getUserId()
    private val mTierType = mSharedPrefUtils.getTierType()

    override suspend fun getSolvedProblems(): Problems {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mBaseNetworkService.getSolvedProblems(mUserId!!)
    }

    override suspend fun getSearchProblemList(problemId: Int): Problems {
        return mBaseNetworkService.getSearchProblemList(problemId)
    }

    override suspend fun getUserStats(): List<Stats> {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mBaseNetworkService.getUserStats(mUserId!!)
    }

    override suspend fun getBOJUserInfo(): List<ProblemStatus> {
        return mBaseNetworkService.getBOJUserInfo()
    }

    override fun setUserInfo(userId: String, password: String, fcmToken: String?) {
        mFakeFirebaseReference.setUserInfo(userId, password, fcmToken)
        mSharedPrefUtils.setUserId(userId)
        mUserId = mSharedPrefUtils.getUserId()
    }

    override fun checkUserInfo(userId: String, password: String): Boolean {
        return mFakeFirebaseReference.checkUserInfo(userId, password)
    }

    override fun getuserInfo(): UserInfo? {
        return mFakeFirebaseReference.getUserInfo(mUserId)
    }

    override fun setDateInfo(): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.setDateInfo(mUserId!!)
    }

    override fun getDateInfoObject(): DateInfoObject? {
        return mFakeFirebaseReference.getDateInfos(mUserId)
    }

    override fun setIdeaInfo(url: String?, comment: String?, problemId: Int): IdeaInfo {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.setIdeaInfo(mUserId!!, url, comment, problemId)
    }

    override fun getIdeaInfos(problemId: Int): IdeaInfos? {
        return mFakeFirebaseReference.getIdeaInfos(mUserId, problemId)
    }

    override fun setComment(problemId: Int, comment: String): CommentInfo {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }
        if (mTierType == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "mTierType"))
        }

        return mFakeFirebaseReference.setComment(mUserId!!, mTierType, problemId, comment)
    }

    override fun getCommentObject(problemId: Int): CommentObject? {
        return mFakeFirebaseReference.getCommentObject(problemId)
    }

    override fun setChildComment(commentId: String, comment: String): ChildCommentInfo {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }
        if (mTierType == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "mTierType"))
        }

        return mFakeFirebaseReference.setChildComment(mUserId!!, mTierType, commentId, comment)
    }

    override fun getChildCommentObject(commentId: String): ChildCommentObject? {
        return mFakeFirebaseReference.getChildCommentObject(commentId)
    }

    override fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): TipProblem {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.setTippingProblem(mUserId!!, problem, isShow, tipComment)
    }

    override fun getTippingProblem(): TippingProblemObject? {
        return mFakeFirebaseReference.getTippingProblemObject(mUserId)
    }

    override fun modifyTippingProblem(
        problemId: Int,
        isShow: Boolean?,
        comment: String?
    ): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.modifyTippingProblem(mUserId!!, problemId, isShow, comment)
    }

    override fun deleteTippingProblem(problemId: Int): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.deleteTippingProblem(mUserId!!, problemId)
    }

}