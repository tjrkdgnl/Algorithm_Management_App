package com.ama.algorithmmanagement.Repositories

import com.ama.algorithmmanagement.Model.UserInfo

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
                return true;
            }
        }

        return false
    }
}