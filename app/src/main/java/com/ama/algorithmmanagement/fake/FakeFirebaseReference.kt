package com.ama.algorithmmanagement.fake

import com.ama.algorithmmanagement.Base.BaseSharedPreference
import com.ama.algorithmmanagement.Model.*
import com.ama.algorithmmanagement.utils.DateUtils
import com.ama.algorithmmanagement.utils.SharedPrefUtils
import timber.log.Timber

class FakeFirebaseReference(
    private val mFakeFirebaseDataProvider: FakeFirebaseDataProvider,
    private val mSharedPrefUtils: BaseSharedPreference
) {

    private val mUserId = mSharedPrefUtils.getUserIdFromLocal()


    fun setUserInfo(userId: String, userPw: String, fcmToken: String?) {
        val userList = mFakeFirebaseDataProvider.userSnapShot

        userList.add(UserInfo(userId, userPw, fcmToken))
    }

    fun getUserInfo(userId: String): UserInfo? {
        val userList = mFakeFirebaseDataProvider.userSnapShot
        for (userInfo in userList) {
            if (userInfo.userId == userId) {
                return userInfo
            }
        }

        return null
    }

    fun checkUserInfo(userId: String, password: String): Boolean {
        val userList = mFakeFirebaseDataProvider.userSnapShot

        for (userInfo in userList) {
            if (userInfo.userId == userId && userInfo.userPw == password) {
                Timber.e("$userId 가 firebase에 존재합니다.")
                return true;
            }
        }
        Timber.e("$userId 가 firebase에 존재하지 않습니다.")
        return false
    }

    fun setDateInfo() {
        val date = DateUtils.createDate()
        val dateInfo = DateInfo(date)

        if (mUserId == null) {
            return
        }

        for (dateInfos in mFakeFirebaseDataProvider.dateSnapShot) {
            if (dateInfos.userId == mUserId) {
                dateInfos.dateList.add(dateInfo)
                return
            }
        }

        val dateInfos = DateInfos(1, mUserId, mutableListOf(dateInfo))
        mFakeFirebaseDataProvider.dateSnapShot.add(dateInfos)
    }

    fun getDateInfos(): DateInfos? {
        for (dateInfos in mFakeFirebaseDataProvider.dateSnapShot) {
            if (dateInfos.userId == mUserId) {
                return dateInfos
            }
        }
        return null
    }


    fun setIdeaInfo(url: String?, comment: String?, problemId: Int) {
        val date = DateUtils.createDate()
        val ideaInfo = IdeaInfo(url, comment, date)
        val ideaInfos = IdeaInfos(1, problemId, mutableListOf(IdeaInfo(url, comment, date)))

        var userCheck = false
        var problemCheck = false

        if (mUserId == null) {
            return
        }

        for (userIdeaInfo in mFakeFirebaseDataProvider.ideaSnapShot) {
            if (userIdeaInfo.userId == mUserId) {
                userCheck = true

                for (ideainfos in userIdeaInfo.ideaInfosList) {
                    if (ideainfos.problemId == problemId) {
                        problemCheck = true
                        ideainfos.ideaList.add(ideaInfo)
                        ideaInfos.count++
                        return
                    }
                }

                if (!problemCheck) {
                    userIdeaInfo.ideaInfosList.add(ideaInfos)
                }
            }
        }

        if (!userCheck) {
            val userIdeaInfo = UserIdeaInfo(mUserId, mutableListOf(ideaInfos))
            mFakeFirebaseDataProvider.ideaSnapShot.add(userIdeaInfo)
        }
    }

    fun getIdeaInfos(problemId: Int): IdeaInfos? {
        for (userIdeaInfo in mFakeFirebaseDataProvider.ideaSnapShot) {
            if (userIdeaInfo.userId == mUserId) {

                for (ideaInfos in userIdeaInfo.ideaInfosList) {
                    if (ideaInfos.problemId == problemId) {
                        return ideaInfos
                    }
                }

            }
        }
        return null
    }

}