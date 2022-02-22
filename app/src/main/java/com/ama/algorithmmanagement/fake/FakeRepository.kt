package com.ama.algorithmmanagement.fake

import android.app.Application
import com.ama.algorithmmanagement.Base.BaseFirebaseService
import com.ama.algorithmmanagement.Base.BaseNetworkService
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Base.BaseSharedPreference
import com.ama.algorithmmanagement.model.*
import com.ama.algorithmmanagement.R
import kotlinx.coroutines.flow.Flow

class FakeRepository(
    private val mApp: Application,
    private val mFakeFirebaseReference: BaseFirebaseService,
    private val mBaseNetworkService: BaseNetworkService,
    private val mSharedPrefUtils: BaseSharedPreference,
) : BaseRepository {

    private var mUserId: String? =
        mSharedPrefUtils.getUserId() ?: mApp.getString(R.string.default_user)
    private val mTierType = mSharedPrefUtils.getTierType()

    override suspend fun getProblem(problemId: Int): TaggedProblem {
        return mBaseNetworkService.getProblem(problemId)
    }

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

    override suspend fun getAutoSearchedData(keyword: String): AutoKeywordObject {
        return mBaseNetworkService.getAutoSearchObject(keyword)
    }


    override suspend fun getUnSolvedProblems(solvedacToken: String?): List<ProblemStatus> {
        if (solvedacToken == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "solvedacToken"))
        }

        return mBaseNetworkService.getUnSolvedProblems(solvedacToken)
    }

    override suspend fun getBOJUserInfo(): User {
        TODO("Not yet implemented")
    }

    override suspend fun setUserInfo(userId: String, password: String, fcmToken: String?): Boolean {
        if (mFakeFirebaseReference.setUserInfo(userId, password, fcmToken)) {
            mSharedPrefUtils.setUserId(userId)
            mUserId = mSharedPrefUtils.getUserId()
            return true
        }
        return false

    }

    override suspend fun confirmUserInfo(userId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signUpUserInfo(userId: String, password: String): Boolean {
        return mFakeFirebaseReference.signUpUserInfo(userId, password)
    }

    override suspend fun getUserInfo(userId: String): UserInfo? {
        return mFakeFirebaseReference.getUserInfo(userId)
    }

    override suspend fun setDateInfo(count:Int): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.setDateInfo(mUserId!!,count)
    }

    override suspend fun getDateObject(): DateObject? {
        return mFakeFirebaseReference.getDateObject(mUserId)
    }

    override suspend fun setIdeaInfo(url: String?, comment: String?, problemId: Int): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.setIdeaInfo(mUserId!!, url, comment, problemId)
    }

    override suspend fun getIdeaInfos(problemId: Int): Flow<IdeaInfos?> {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.getIdeaInfos(mUserId!!, problemId)
    }

    override suspend fun setComment(problemId: Int, comment: String): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }
        if (mTierType == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "mTierType"))
        }

        return mFakeFirebaseReference.setComment(mUserId!!, mTierType, problemId, comment)
    }

    override suspend fun getCommentObject(problemId: Int): CommentObject? {
        return mFakeFirebaseReference.getCommentObject(problemId)
    }

    override suspend fun setChildComment(problemId: Int,commentId: String, comment: String): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }
        if (mTierType == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "mTierType"))
        }

        return mFakeFirebaseReference.setChildComment(problemId,mUserId!!, mTierType, commentId, comment)
    }

    override suspend fun getChildCommentObject(commentId: String): ChildCommentObject? {
        return mFakeFirebaseReference.getChildCommentObject(commentId)
    }

    override suspend fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.setTippingProblem(mUserId!!, problem, isShow, tipComment)
    }

    override suspend fun initTipProblems(problems: List<TaggedProblem>): TippingProblemObject? {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.initTipProblems(mUserId!!,problems)
    }

    override suspend fun getTippingProblem(): TippingProblemObject? {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.getTippingProblemObject(mUserId!!)
    }

    override suspend fun getNotTippingProblem(): TippingProblemObject? {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.getNotTippingProblemObject(mUserId!!)
    }

    override suspend fun modifyTippingProblem(
        problemId: Int,
        isShow: Boolean,
        tipComment: String?
    ): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.modifyTippingProblem(mUserId!!, problemId, isShow, tipComment)
    }

    override suspend fun deleteTippingProblem(problemId: Int): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFakeFirebaseReference.deleteTippingProblem(mUserId!!, problemId)
    }

}