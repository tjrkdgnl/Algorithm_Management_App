package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.Model.UserInfo
import timber.log.Timber

class FakeFirebaseReference {
    val userInfos: MutableList<UserInfo> by lazy {
        mutableListOf()
    }

    fun setUserInfo(userId: String, userPw: String, fcmToken: String?) {
        userInfos.add(UserInfo(userId, userPw, fcmToken))
    }

    fun getUserInfo(userId: String): UserInfo? {
        for (userInfo in userInfos) {
            if (userInfo.userId == userId) {
                return userInfo
            }
        }

        return null
    }

    fun checkUserInfo(userId: String, password: String): Boolean {
        for (userInfo in userInfos) {
            if (userInfo.userId == userId && userInfo.userPw == password) {
                Timber.e("$userId 가 firebase에 존재합니다.")
                return true;
            }
        }
        Timber.e("$userId 가 firebase에 존재하지 않습니다.")
        return false
    }
}