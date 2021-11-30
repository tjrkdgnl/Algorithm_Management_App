package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.Application.AMAApplication
import com.ama.algorithmmanagement.Base.BaseRepository
import com.ama.algorithmmanagement.Model.SolvedAlgorithms
import com.ama.algorithmmanagement.Model.UserInfo
import com.ama.algorithmmanagement.Network.NetworkService
import kotlinx.coroutines.delay

class FakeRepository : BaseRepository {
    private val mNetworkService: NetworkService = object : NetworkService() {
        override suspend fun getSolvedProblems(
            userId: String
        ): SolvedAlgorithms {
            //fake network delay
            delay(1000)
            return DataProvider.getSolvedAlgorithms()
        }
    }

    private val mFakeFirebaseReference = FakeFirebaseReference()
    private val mSharedPrefUtils = AMAApplication.INSTANCE.sharedPrefUtils

    override suspend fun getSolvedProblems(userId: String): SolvedAlgorithms {
        return mNetworkService.getSolvedProblems(userId)
    }

    fun setUserInfo(userId: String, password: String, fcmToken: String? = null) {
        mFakeFirebaseReference.setUserInfo(userId, password, fcmToken)
        //로컬에 userId저장
        mSharedPrefUtils.setUserId(userId)
    }

    fun checkUserInfo(userId: String, password: String) {
        mFakeFirebaseReference.checkUserInfo(userId, password)
    }

    fun getuserInfo(): UserInfo? {
        return mSharedPrefUtils.getUserId()?.let { id ->
            mFakeFirebaseReference.getUserInfo(id)
        }
    }

}