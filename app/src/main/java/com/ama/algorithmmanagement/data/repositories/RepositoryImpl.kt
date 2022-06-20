package com.ama.algorithmmanagement.data.repositories

import android.app.Application
import com.ama.algorithmmanagement.R
import com.ama.algorithmmanagement.data.network.KAPIGenerator
import com.ama.algorithmmanagement.domain.base.BaseFirebaseService
import com.ama.algorithmmanagement.domain.base.BaseNetworkService
import com.ama.algorithmmanagement.domain.base.BaseRepository
import com.ama.algorithmmanagement.domain.base.BaseSharedPreference
import com.ama.algorithmmanagement.domain.entity.*
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class RepositoryImpl(
    private val mApp: Application,
    private val mFirebaseService: BaseFirebaseService,
    private val mNetworkService: BaseNetworkService,
    private val mSharedPrefUtils: BaseSharedPreference,
) : BaseRepository {

    private var mUserId = mSharedPrefUtils.getUserId()
    private val mTiertype = mSharedPrefUtils.getTierType()

    override fun updateRetrofitWithToken(token: String) {
        mNetworkService.updateToken(token)
    }

    //NetworkService
    override suspend fun getProblem(problemId: Int): TaggedProblem {
        return mNetworkService.getProblem(problemId)
    }

    override suspend fun getSolvedProblems(): Problems {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        val solvedByUser = mApp.getString(R.string.solvedProblemUserId, mUserId)

        return mNetworkService.getSolvedProblems(solvedByUser)
    }

    override suspend fun getUserStats(): List<Stats> {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mNetworkService.getUserStats(mUserId!!)
    }

    override suspend fun getAutoSearchedData(keyword: String): AutoKeywordObject {
        return KAPIGenerator.getInstance().getAutoKeyword(keyword)
    }

    override suspend fun getSearchProblemList(problemId: Int): Problems {
        return mNetworkService.getSearchProblemList(problemId)
    }

    override suspend fun getUnSolvedProblems(solvedacToken: String?): List<ProblemStatus> {
        if (solvedacToken == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "solvedacToken"))
        }

        return mNetworkService.getUnSolvedProblems(solvedacToken)
    }

    override suspend fun getBOJUserInfo(): User {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "mUserId"))
        }

        return mNetworkService.getUserInfo(mUserId!!)
    }

    //Firebase Service
    override suspend fun setUserInfo(
        userId: String,
        password: String,
        fcmToken: String?,
        solvedToken: String
    ): Boolean {
        return if (mFirebaseService.setUserInfo(userId, password, fcmToken, solvedToken)) {
            mSharedPrefUtils.setUserId(userId)
            mUserId = mSharedPrefUtils.getUserId()

            true
        } else {
            false
        }
    }

    override suspend fun confirmUserInfo(userId: String): Boolean {
        return kotlin.runCatching {
            mNetworkService.getUserInfo(userId)
            true
        }.onFailure {
            Timber.e(it)
        }.getOrDefault(false)
    }

    override suspend fun signUpUserInfo(userId: String, password: String): Boolean {
        return mFirebaseService.signUpUserInfo(userId, password)
    }

    override suspend fun getUserInfo(userId: String): UserInfo? {
        return mFirebaseService.getUserInfo(userId)
    }

    override suspend fun setDateInfo(count: Int): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFirebaseService.setDateInfo(mUserId!!, count)
    }

    override suspend fun getDateObject(): DateObject? {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFirebaseService.getDateObject(mUserId!!)
    }

    override suspend fun setIdeaInfo(url: String?, comment: String?, problemId: Int): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFirebaseService.setIdeaInfo(mUserId!!, url, comment, problemId)
    }

    override suspend fun getIdeaInfos(problemId: Int): Flow<IdeaInfos?> {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFirebaseService.getIdeaInfos(mUserId!!, problemId)
    }

    override suspend fun setComment(problemId: Int, comment: String): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        if (mTiertype == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "tierType"))
        }

        return mFirebaseService.setComment(mUserId!!, mTiertype, problemId, comment)
    }

    override suspend fun getCommentObject(problemId: Int): CommentObject? {
        return mFirebaseService.getCommentObject(problemId)
    }

    override suspend fun setChildComment(
        problemId: Int,
        commentId: String,
        comment: String
    ): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }
        if (mTiertype == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "tierType"))
        }

        return mFirebaseService.setChildComment(problemId, mUserId!!, mTiertype, commentId, comment)
    }

    override suspend fun getChildCommentObject(commentId: String): ChildCommentObject? {
        return mFirebaseService.getChildCommentObject(commentId)
    }

    override suspend fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFirebaseService.setTippingProblem(mUserId!!, problem, isShow, tipComment)
    }

    override suspend fun initTipProblems(problems: List<TaggedProblem>): TippingProblemObject? {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFirebaseService.initTipProblems(mUserId!!, problems)
    }

    override suspend fun getAllTipProblems(): TippingProblemObject? {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFirebaseService.getAllTipProblems(mUserId!!)
    }

    override suspend fun getTippingProblem(): TippingProblemObject? {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFirebaseService.getTippingProblemObject(mUserId!!)
    }

    override suspend fun getNotTippingProblem(): TippingProblemObject? {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFirebaseService.getNotTippingProblemObject(mUserId!!)
    }

    override suspend fun modifyTippingProblem(
        problemId: Int,
        isShow: Boolean,
        tipComment: String?
    ): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFirebaseService.modifyTippingProblem(mUserId!!, problemId, isShow, tipComment)
    }

    override suspend fun deleteTippingProblem(problemId: Int): Boolean {
        if (mUserId == null) {
            throw NullPointerException(mApp.getString(R.string.objectIsNull, "userId"))
        }

        return mFirebaseService.deleteTippingProblem(mUserId!!, problemId)
    }
}
