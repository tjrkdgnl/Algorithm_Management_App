package com.ama.algorithmmanagement.Repositories

import android.app.Application
import com.ama.algorithmmanagement.Base.BaseFirebaseService
import com.ama.algorithmmanagement.Base.BaseNetworkService
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Base.BaseSharedPreference
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.R

class Repository(
    private val mApp:Application,
    private val mFirebaseService: BaseFirebaseService,
    private val mNetworkService: BaseNetworkService,
    private val mSharedPrefUtils: BaseSharedPreference,
) : BaseRepository {

    private var mUserId = mSharedPrefUtils.getUserId()
    private val mTiertype = mSharedPrefUtils.getTierType()

    //NetworkService
    override suspend fun getProblem(problemId: Int): TaggedProblem {
        return mNetworkService.getProblem(problemId)
    }

    override suspend fun getSolvedProblems(): Problems {
        if(mUserId ==null){
            throw NullPointerException(mApp.getString(R.string.objectIsNull,"userId"))
        }

        val solvedByUser = mApp.getString(R.string.solvedProblemUserId,mUserId)

        return mNetworkService.getSolvedProblems(solvedByUser)
    }

    override suspend fun getUserStats(): List<Stats> {
        if(mUserId ==null){
            throw NullPointerException(mApp.getString(R.string.objectIsNull,"userId"))
        }

        return mNetworkService.getUserStats(mUserId!!)
    }

    override suspend fun getSearchProblemList(problemId: Int): Problems {
        return mNetworkService.getSearchProblemList(problemId)
    }

    override suspend fun getUnSolvedProblems(solvedacToken:String?): List<ProblemStatus> {
        if(solvedacToken ==null){
            throw NullPointerException(mApp.getString(R.string.objectIsNull,"solvedacToken"))
        }

        return mNetworkService.getUnSolvedProblems(solvedacToken)
    }

    //Firebase Service
    override suspend fun setUserInfo(userId: String, password: String, fcmToken: String?) :Boolean {
        return if(mFirebaseService.setUserInfo(userId, password, fcmToken)){
            mSharedPrefUtils.setUserId(userId)
            mUserId = mSharedPrefUtils.getUserId()

            true
        } else{
            false
        }
    }

    override suspend fun getUserInfo(): UserInfo? {
        if (mUserId == null) {
            return null
        }

        return mFirebaseService.getUserInfo(mUserId!!)
    }

    override suspend fun checkUserInfo(userId: String, password: String): Boolean {
        return mFirebaseService.checkUserInfo(userId,password)
    }

    override fun setDateInfo(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDateInfoObject(): DateInfoObject? {
        TODO("Not yet implemented")
    }

    override fun setIdeaInfo(url: String?, comment: String?, problemId: Int): IdeaInfo {
        TODO("Not yet implemented")
    }

    override fun getIdeaInfos(problemId: Int): IdeaInfos? {
        TODO("Not yet implemented")
    }

    override fun setComment(problemId: Int, comment: String): CommentInfo {
        TODO("Not yet implemented")
    }

    override fun getCommentObject(problemId: Int): CommentObject? {
        TODO("Not yet implemented")
    }

    override fun setChildComment(commentId: String, comment: String): ChildCommentInfo {
        TODO("Not yet implemented")
    }

    override fun getChildCommentObject(commentId: String): ChildCommentObject? {
        TODO("Not yet implemented")
    }

    override fun setTippingProblem(
        problem: TaggedProblem,
        isShow: Boolean,
        tipComment: String?
    ): TipProblem {
        TODO("Not yet implemented")
    }

    override fun getTippingProblem(): TippingProblemObject? {
        TODO("Not yet implemented")
    }

    override fun modifyTippingProblem(
        problemId: Int,
        isShow: Boolean,
        tipComment: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteTippingProblem(problemId: Int): Boolean {
        TODO("Not yet implemented")
    }
}